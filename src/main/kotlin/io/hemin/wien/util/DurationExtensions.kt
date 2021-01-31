package io.hemin.wien.util

import java.time.Duration

/** Returns `true` when this duration is either `null` or negative. */
internal fun Duration?.isNullOrNegative(): Boolean = this == null || this.isNegative

/** Returns `true` when this duration is either `null` or zero or negative. */
internal fun Duration?.isNullOrNotPositive(): Boolean = this == null || this.isZero || this.isNegative
