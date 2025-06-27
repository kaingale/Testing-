package utilities;

public class RetryUtil {
	    
	    public static <T> T retry(RetryableOperation<T> operation, int maxAttempts, long delayMillis) throws Exception {
	        Exception lastException = null;
	        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
	            try {
	                return operation.execute();
	            } catch (Exception e) {
	                lastException = e;
	                if (attempt < maxAttempts) {
	                    Thread.sleep(delayMillis);
	                }
	            }
	        }
	        throw lastException;
	    }
	    
	    @FunctionalInterface
	    public interface RetryableOperation<T> {
	        T execute() throws Exception;
	    }
	}


