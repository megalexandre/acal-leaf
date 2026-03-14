package acal.com.acal_left.ui.report;

import net.sf.jasperreports.engine.JasperCompileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Invoked by exec-maven-plugin during the generate-resources phase.
 * Compiles all .jrxml files in the source directory to .jasper files
 * in the output directory.
 *
 * Args: <sourceDir> <outputDir>
 */
public class JasperReportCompiler {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: JasperReportCompiler <sourceDir> <outputDir>");
        }

        File sourceDir = new File(args[0]);
        File outputDir = new File(args[1]);

        if (!sourceDir.exists()) {
            System.out.println("[jasper] Source directory not found, skipping: " + sourceDir);
            return;
        }

        outputDir.mkdirs();

        File[] jrxmlFiles = sourceDir.listFiles((dir, name) -> name.endsWith(".jrxml"));
        if (jrxmlFiles == null || jrxmlFiles.length == 0) {
            System.out.println("[jasper] No .jrxml files found in " + sourceDir);
            return;
        }

        // sort for deterministic output
        Arrays.sort(jrxmlFiles);

        int compiled = 0;
        int failed = 0;

        for (File jrxml : jrxmlFiles) {
            String baseName = jrxml.getName().replace(".jrxml", ".jasper");
            File outputFile = new File(outputDir, baseName);

            try (InputStream in = new FileInputStream(jrxml);
                 FileOutputStream out = new FileOutputStream(outputFile)) {

                JasperCompileManager.compileReportToStream(in, out);
                System.out.println("[jasper] Compiled: " + jrxml.getName() + " -> " + baseName);
                compiled++;

            } catch (Exception e) {
                System.err.println("[jasper] FAILED: " + jrxml.getName() + " - " + e.getMessage());
                failed++;
            }
        }

        System.out.printf("[jasper] Done: %d compiled, %d failed%n", compiled, failed);

        if (failed > 0) {
            throw new RuntimeException(failed + " report(s) failed to compile. See output above.");
        }
    }
}

