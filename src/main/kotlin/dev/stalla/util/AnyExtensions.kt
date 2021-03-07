package dev.stalla.util

import kotlin.contracts.contract

/** Check if all argument elements are not null. */
@InternalApi
internal fun allNotNull(a: Any?): Boolean {
    contract {
        returns(true) implies (a != null)
        returns(false) implies (a == null)
    }
    return a != null
}

/** Check if all argument elements are not null. */
@InternalApi
internal fun allNotNull(a: Any?, b: Any?): Boolean {
    contract {
        returns(true) implies (a != null && b != null)
    }
    return a != null && b != null
}

/** Check if all argument elements are not null. */
@InternalApi
internal fun allNotNull(a: Any?, b: Any?, c: Any?): Boolean {
    contract {
        returns(true) implies (
            a != null &&
                b != null &&
                c != null
            )
    }
    return a != null && b != null && c != null
}

/** Check if all argument elements are not null. */
@InternalApi
internal fun allNotNull(a: Any?, b: Any?, c: Any?, d: Any?): Boolean {
    contract {
        returns(true) implies (
            a != null &&
                b != null &&
                c != null &&
                d != null
            )
    }
    return a != null && b != null && c != null && d != null
}

/** Check if all argument elements are not null. */
@InternalApi
internal fun allNotNull(vararg elements: Any?): Boolean = elements.all { p -> p != null }

/** Check if at least one argument element is not null. */
@InternalApi
internal fun anyNotNull(vararg elements: Any?): Boolean = elements.any { p -> p != null }

/** Check if all argument elements are null. */
@InternalApi
internal fun allNull(a: Any?): Boolean {
    contract {
        returns(true) implies (a == null)
        returns(false) implies (a != null)
    }
    return a == null
}

/** Check if all argument elements are null. */
@InternalApi
internal fun allNull(a: Any?, b: Any?): Boolean {
    contract {
        returns(true) implies (a == null && b == null)
    }
    return a == null && b == null
}

/** Check if all argument elements are null. */
@InternalApi
internal fun allNull(a: Any?, b: Any?, c: Any?): Boolean {
    contract {
        returns(true) implies (
            a == null &&
                b == null &&
                c == null
            )
    }
    return a == null && b == null && c == null
}

/** Check if all argument elements are null. */
@InternalApi
internal fun allNull(a: Any?, b: Any?, c: Any?, d: Any?): Boolean {
    contract {
        returns(true) implies (
            a == null &&
                b == null &&
                c == null &&
                d == null
            )
    }
    return a == null && b == null && c == null && d == null
}

/** Check if all argument elements are null. */
@InternalApi
internal fun allNull(vararg elements: Any?): Boolean = elements.all { p -> p == null }

/** Check if at least one argument element is null. */
@InternalApi
internal fun anyNull(vararg elements: Any?): Boolean = elements.any { p -> p == null }
