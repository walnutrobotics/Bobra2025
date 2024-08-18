package com.millburnx.utils

import java.awt.Graphics2D
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class Utils {
    companion object {
        /**
         * Normalize an angle to be between -pi and pi radians (-180 to 180 degrees)
         */
        fun normalizeAngle(angle: Double): Double {
            return atan2(sin(angle), cos(angle))
        }

        /**
         * Converts a list of points to a list of Béziers
         */
        fun pathToBeziers(path: List<Vec2d>): MutableList<Bezier> {
            val beziers = mutableListOf<Bezier>()
            for (i in 0 until path.size - 3 step 3) {
                beziers.add(Bezier(path[i], path[i + 1], path[i + 2], path[i + 3]))
            }
            return beziers
        }

        fun drawPoint(g2d: Graphics2D, ppi: Double, point: Vec2d, size: Double, filled: Boolean = true) {
            val center = point * ppi
            val size = size * ppi
            val origin = center - size / 2
            if (filled) {
                g2d.fillOval(origin.x.toInt(), origin.y.toInt(), size.toInt(), size.toInt())
            } else {
                g2d.drawOval(origin.x.toInt(), origin.y.toInt(), size.toInt(), size.toInt())
            }
        }

        fun drawLine(g2d: Graphics2D, ppi: Double, start: Vec2d, end: Vec2d) {
            val start = start * ppi
            val end = end * ppi
            g2d.drawLine(start.x.toInt(), start.y.toInt(), end.x.toInt(), end.y.toInt())
        }
    }
}