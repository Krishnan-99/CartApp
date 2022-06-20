package com.assessment.cartapp.model

/**
 * A generic class that holds a value with its loading status.
 *
 * @param <T>
</T> */
class Resource<T>(status: Status, data: T, message: String?, responseCode: Int) {
    val status: Status
    val responseCode: Int
    val message: String?
    val data: T
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val resource = o as Resource<*>
        if (status !== resource.status) {
            return false
        }
        if (responseCode != resource.responseCode) {
            return false
        }
        if (if (message != null) message != resource.message else resource.message != null) {
            return false
        }
        return if (data != null) data == resource.data else resource.data == null
    }

    override fun hashCode(): Int {
        var result: Int = status.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}'
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null, 200)
        }

        fun <T> success(data: T, message: String?): Resource<T> {
            return Resource(Status.SUCCESS, data, message, 0)
        }

        fun <T> error(msg: String?, data: T): Resource<T> {
            return Resource(Status.ERROR, data, msg, 0)
        }

        fun <T> loading(data: T): Resource<T> {
            return Resource(Status.LOADING, data, null, 0)
        }
    }

    init {
        this.status = status
        this.data = data
        this.message = message
        this.responseCode = responseCode
    }
}