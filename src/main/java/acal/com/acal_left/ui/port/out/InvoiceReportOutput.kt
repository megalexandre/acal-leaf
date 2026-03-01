package acal.com.acal_left.ui.port.out

import acal.com.acal_left.model.Invoice

data class InvoiceReportOutput(
    val numeroconta: String = "",
    val data: String = "",
    val pagamento: String = "",
    val vencimento: String = "",
    val MesReferente: String = "",
    val AnoReferente: String = "",
    val socio: String = "",
    val numeroSocio: String = "",
    val categoriaSocio: String = "",
    val endereco: String = "",
    val numero: String = "",
    val taxaSocio: String = "",
    val taxas: String = "",
    val consumo: String = "",
    val excessoLTd: String = "",
    val excessoValor: String = "",
    val totalconta: String = ""
)

fun Invoice.toReportOutput(): InvoiceReportOutput {
    return InvoiceReportOutput(
        numeroconta = "",
        data = "",
        pagamento = "",
        vencimento = "",
        MesReferente = "",
        AnoReferente = "",
        socio = "",
        numeroSocio = "",
        categoriaSocio = "",
        endereco = "",
        numero = "",
        taxaSocio = "",
        taxas = "",
        consumo = "",
        excessoLTd = "",
        excessoValor = "",
        totalconta = ""
    )
}

fun List<Invoice>.toReportOutput(): List<InvoiceReportOutput> =
    this.map { it.toReportOutput() }