package com.example.dmhelper.presentation.common

import android.graphics.RuntimeShader

val FROSTED_GLASS_SHADER = RuntimeShader("""
    uniform shader inputShader;
    uniform float height;
    uniform float width;
            
    vec4 main(vec2 coords) {
        vec4 currValue = inputShader.eval(coords);
        float top = height - 100;
        if (coords.y < top) {
            return currValue;
        } else {
            // Avoid blurring edges
            if (coords.x > 1 && coords.y > 1 &&
                    coords.x < (width - 1) &&
                    coords.y < (height - 1)) {
                // simple box blur - average 5x5 grid around pixel
                vec4 boxSum =
                    inputShader.eval(coords + vec2(-2, -2)) + 
                    // ...
                    currValue +
                    // ...
                    inputShader.eval(coords + vec2(2, 2));
                currValue = boxSum / 25;
            }
            
            const vec4 white = vec4(1);
            // top-left corner of label area
            vec2 lefttop = vec2(0, top);
            float lightenFactor = min(1.0, .6 *
                    length(coords - lefttop) /
                    (0.85 * length(vec2(width, 100))));
            // White in upper-left, blended increasingly
            // toward lower-right
            return mix(currValue, white, 1 - lightenFactor);
        }
    }
""")