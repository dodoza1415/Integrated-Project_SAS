package int221.YuuuHooo.project.exceptions;

import java.util.List;

public class ErrorResponseWrapper {
    private List<ErrorResponse> detail;

    public ErrorResponseWrapper(List<ErrorResponse> detail) {

        this.detail = detail;
    }

    public List<ErrorResponse> getDetail() {
        return detail;
    }
}
