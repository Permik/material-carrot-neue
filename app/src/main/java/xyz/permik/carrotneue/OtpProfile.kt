package xyz.permik.carrotneue

data class OtpProfile(
    val id: Int,
    val name: String,
    val secret: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OtpProfile

        if (id != other.id) return false
        if (name != other.name) return false
        if (!secret.contentEquals(other.secret)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + secret.contentHashCode()
        return result
    }
}
