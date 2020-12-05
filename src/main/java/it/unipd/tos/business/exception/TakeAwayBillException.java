////////////////////////////////////////////////////////////////////
// [ALESSANDRO] [FLORI] [1186916]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Throwable{

    private static final long serialVersionUID = 1L;
    private String error;

    public TakeAwayBillException(String error) {
        super(error);
    }

    @Override
    public String getMessage() {
        return error;
    }
    
}
