#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;

vec4 rgba(vec2 offset) {
    return v_color * texture2D(u_texture, v_texCoords + offset);
}

void main() {
    vec4 outputColor = rgba(vec2(0, 0));



    gl_FragColor = vec4(outputColor.g, outputColor.b, outputColor.r, outputColor.a);
}