package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationModel {
    private int currentPage = 0;
    private int pageSize = 100;
    private int totalItems = 0;

    public int getTotalPages() {
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    public int getStartIndex() {
        return currentPage * pageSize;
    }

    public int getEndIndex() {
        return Math.min((currentPage + 1) * pageSize, totalItems);
    }

    public boolean hasNextPage() {
        return currentPage < getTotalPages() - 1;
    }

    public boolean hasPreviousPage() {
        return currentPage > 0;
    }

    public void nextPage() {
        if (hasNextPage()) {
            currentPage++;
        }
    }

    public void previousPage() {
        if (hasPreviousPage()) {
            currentPage--;
        }
    }

    public void firstPage() {
        currentPage = 0;
    }

    public void lastPage() {
        currentPage = getTotalPages() - 1;
    }
}

