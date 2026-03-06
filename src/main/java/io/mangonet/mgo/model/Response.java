package io.mangonet.mgo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.mangonet.mgo.protocol.deserializer.KeepAsJsonDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    private long id;
    private String jsonrpc;
    private T result;
    private Error error;

    public Response() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public static class Error {
        
        public static final Error PARSE_ERROR = new Error(-32700, "JSON parse error");
        public static final Error INVALID_REQUEST = new Error(-32600, "Invalid request");
        public static final Error METHOD_NOT_FOUND = new Error(-32601, "Method not found");
        public static final Error INVALID_PARAMS = new Error(-32602, "Invalid parameters");
        public static final Error INTERNAL_ERROR = new Error(-32603, "Internal error");
        private static final long serialVersionUID = 4682571044532698806L;
        
        private int code;
        private String message;

        @JsonDeserialize(using = KeepAsJsonDeserializer.class)
        private String data;

        public Error() {}

        public Error(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Error)) {
                return false;
            }

            Error error = (Error) o;

            if (getCode() != error.getCode()) {
                return false;
            }
            if (getMessage() != null
                    ? !getMessage().equals(error.getMessage())
                    : error.getMessage() != null) {
                return false;
            }
            return getData() != null ? getData().equals(error.getData()) : error.getData() == null;
        }

        @Override
        public int hashCode() {
            int result = getCode();
            result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
            result = 31 * result + (getData() != null ? getData().hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
