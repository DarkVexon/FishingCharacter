#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
//varying vec4 v_pos;
//varying vec4 v_apos;

uniform sampler2D u_texture;
uniform float u_scale;//settings dot scale
uniform vec2 u_screenSize;//width, height
uniform float x_time;

vec4 rgba(vec2 offset) {
    return v_color * texture2D(u_texture, v_texCoords + offset);
}

void main() {
    vec4 outputColor = rgba(vec2(0, 0));//Creates the colour of the pixel
    float magnitude = 0.4;
    float val = magnitude * v_texCoords.x + 1.0;//Equation of the line y=mx+c

    //(s)    (1)  (2)
    float tmp = mod(x_time / 3.0, 1.5);//(1)Use this value to slow down the line (higher = slower) | (2)Changing this affects the range of y-values the line covers

    val = val - tmp;

    /*
    if (val - v_texCoords.y < 0.03 && val - v_texCoords.y > -0.03) //If Pixel within 0.03 of line then:
    {
        outputColor.rgb = outputColor.rgb + 0.2;    //Make the pixel whiter
        0.2*smoothstep(val - tmp, val - tmp - 0.03, 0.0);
        outputColor.rgb = outputColor.rgb + (0.1 * (abs(val - v_texCoords.y) / 0.03));
    }
    */

    if (outputColor.a > 0) {
        if (val - v_texCoords.y < 0.03 && val - v_texCoords.y > -0.03)
        {
            outputColor.r = outputColor.r + 0.1;
        }
        else if (val - v_texCoords.y < 0.06 && val - v_texCoords.y > -0.06)
        {
            outputColor.g = outputColor.g + 0.1;
        }
        else if (val - v_texCoords.y < 0.09 && val - v_texCoords.y > -0.09)
        {
            outputColor.b = outputColor.b + 0.1;
        }
    }

    gl_FragColor = vec4(outputColor.r, outputColor.g, outputColor.b, outputColor.a);
}