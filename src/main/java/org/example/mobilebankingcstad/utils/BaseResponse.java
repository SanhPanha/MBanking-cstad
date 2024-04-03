package org.example.mobilebankingcstad.utils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Accessors(chain = true)
@Data
public class BaseResponse<T> {
    private T payload;
    private String message;
    private Object metadata; // related to pagination
    private  int status;
    public static <T> BaseResponse<T> createSuccess(){
        return new BaseResponse<T>()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Successfully Created");
    }
    public static <T> BaseResponse<T> ok(){
        return new BaseResponse<T>()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Successfully Created");
    }

    public static <T> BaseResponse<T> notFound(){
        return new BaseResponse<T>()
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setMessage("No Item Found");
    }

    public static <T> BaseResponse<T> badRequest(){
        return new BaseResponse<T>()
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setMessage("Bad Request Provided");
    }

    public static <T> BaseResponse<T> updateSuccess(){
        return new BaseResponse<T>()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Update Successfully");
    }

    public static <T> BaseResponse<T> disableSuccess(){
        return new BaseResponse<T>()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Disable Successfully");
    }

    public static <T> BaseResponse<T> enableSuccess(){
        return new BaseResponse<T>()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Enable Successfully");
    }
}
