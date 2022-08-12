package theFishing.util;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Shaders {
    public static final String vertexShaderHsluv =
            "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
                    + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
                    + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
                    + "uniform mat4 u_projTrans;\n"
                    + "varying vec4 v_color;\n"
                    + "varying vec2 v_texCoords;\n"
                    +
                    "const vec3 epsilon = vec3(0.0088564516790356308);\n" +
                    "const float kappa = 9.032962962;\n" +
                    "const mat3 m =" +
                    "         mat3(+3.240969941904521, -1.537383177570093, -0.498610760293000,\n" +
                    "              -0.969243636280870, +1.875967501507720, +0.041555057407175,\n" +
                    "              +0.055630079696993, -0.203976958888970, +1.056971514242878);\n" +
                    "float intersectLength (float sn, float cs, float line1, float line2) {\n" +
                    "    return line2 / (sn - line1 * cs);\n" +
                    "}\n" +
                    "float chromaLimit(float hue, float lightness) {\n" +
                    "        float sn = sin(hue);\n" +
                    "        float cs = cos(hue);\n" +
                    "        float sub1 = (lightness + 0.16) / 1.16;\n" +
                    "        sub1 *= sub1 * sub1;\n" +
                    "        float sub2 = sub1 > epsilon.x ? sub1 : lightness / kappa;\n" +
                    "        float mn = 1.0e20;\n" +
                    "        vec3 ms = m[0] * sub2;\n" +
                    "        float msy, top1, top2, bottom, length;\n" +
                    "        msy = ms.y;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        msy -= 1.0;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        ms = m[1] * sub2;\n" +
                    "        msy = ms.y;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        msy -= 1.0;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        ms = m[2] * sub2;\n" +
                    "        msy = ms.y;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        msy -= 1.0;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        return mn;\n" +
                    "}\n" +
                    "vec3 hsl2luv(vec3 c)\n" +
                    "{\n" +
                    "    float L = c.z;\n" +
                    "    float C = chromaLimit(c.x, L) * c.y;\n" +
                    "    float U = cos(c.x) * C;\n" +
                    "    float V = sin(c.x) * C;\n" +
                    "    return vec3(L, U, V);\n" +
                    "}\n"
                    + "void main()\n"
                    + "{\n"
                    + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
                    + "   v_color.w = v_color.w * (255.0/254.0);\n"
                    + "   v_color.x *= 6.2831;\n"
                    + "   v_color.rgb = hsl2luv(v_color.rgb);\n"
                    + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
                    + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
                    + "}\n";

    public static String fragmentShaderHsluv =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const vec3 forward = vec3(1.0 / 3.0);\n" +
                    "const vec3 sRGBFrom = vec3(2.4);\n" +
                    "const vec3 sRGBThresholdFrom = vec3(0.04045);\n" +
                    "const vec3 sRGBTo = vec3(1.0 / 2.4);\n" +
                    "const vec3 sRGBThresholdTo = vec3(0.0031308);\n" +
                    "const vec3 epsilon = vec3(0.0088564516790356308);\n" +
                    "const float kappa = 9.032962962;\n" +
                    "const vec2 refUV = vec2(0.19783000664283681, 0.468319994938791);\n" +
                    "const mat3 m =" +
                    "         mat3(+3.240969941904521, -1.537383177570093, -0.498610760293000,\n" +
                    "              -0.969243636280870, +1.875967501507720, +0.041555057407175,\n" +
                    "              +0.055630079696993, -0.203976958888970, +1.056971514242878);\n" +
                    "const mat3 mInv =\n" +
                    "         mat3(0.41239079926595948 , 0.35758433938387796, 0.180480788401834290,\n" +
                    "              0.21263900587151036 , 0.71516867876775593, 0.072192315360733715,\n" +
                    "              0.019330818715591851, 0.11919477979462599, 0.950532152249660580);\n" +
                    "vec3 linear(vec3 t){ return mix(pow((t + 0.055) * (1.0 / 1.055), sRGBFrom), t * (1.0/12.92), step(t, sRGBThresholdFrom)); }\n" +
                    "vec3 sRGB(vec3 t){ return mix(1.055 * pow(t, sRGBTo) - 0.055, 12.92*t, step(t, sRGBThresholdTo)); }\n" +
                    "float xyzF(float t){ return mix(pow(t,1./3.), 7.787037 * t + 0.139731, step(t, epsilon.x)); }\n" +
                    "vec3 xyzF(vec3 t){ return mix(pow(t, forward), 7.787037 * t + 0.139731, step(t, epsilon)); }\n" +
                    "float xyzR(float t){ return mix(t*t*t , 0.1284185 * (t - 0.139731), step(t, 0.20689655)); }\n" +
                    "float intersectLength (float sn, float cs, float line1, float line2) {\n" +
                    "    return line2 / (sn - line1 * cs);\n" +
                    "}\n" +
                    "float chromaLimit(float hue, float lightness) {\n" +
                    "        float sn = sin(hue);\n" +
                    "        float cs = cos(hue);\n" +
                    "        float sub1 = (lightness + 0.16) / 1.16;\n" +
                    "        sub1 *= sub1 * sub1;\n" +
                    "        float sub2 = sub1 > epsilon.x ? sub1 : lightness / kappa;\n" +
                    "        float mn = 1.0e20;\n" +
                    "        vec3 ms = m[0] * sub2;\n" +
                    "        float msy, top1, top2, bottom, length;\n" +
                    "        msy = ms.y;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        msy -= 1.0;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        ms = m[1] * sub2;\n" +
                    "        msy = ms.y;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        msy -= 1.0;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        ms = m[2] * sub2;\n" +
                    "        msy = ms.y;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        msy -= 1.0;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        return mn;\n" +
                    "}\n" +
                    "vec3 rgb2luv(vec3 c)\n" +
                    "{\n" +
                    "    c *= mInv;" +
                    "    float L = max(0.,1.16*pow(c.y, 1.0 / 3.0) - 0.16);\n" +
                    "    vec2 uv;\n" +
                    "    if(L < 0.0001) uv = vec2(0.0);\n" +
                    "    else uv = 13. * L * (vec2(4., 9.) * c.xy / (c.x + 15. * c.y + 3. * c.z) - refUV);\n" +
                    "    return vec3(L, uv);\n" +
                    "}\n" +
                    "float forwardLight(float L) {\n" +
                    "        const float shape = 0.8528, turning = 0.1;\n" +
                    "        float d = turning - L;\n" +
                    "        return mix(\n" +
                    "          ((1. - turning) * (L - 1.)) / (1. - (L + shape * d)) + 1.,\n" +
                    "          (turning * L) / (1.0e-20 + (L + shape * d)),\n" +
                    "          step(0.0, d));\n" +
                    "}\n" +
                    "float reverseLight(float L) {\n" +
                    "        const float shape = 1.1726, turning = 0.1;\n" +
                    "        float d = turning - L;\n" +
                    "        return mix(\n" +
                    "          ((1. - turning) * (L - 1.)) / (1. - (L + shape * d)) + 1.,\n" +
                    "          (turning * L) / (1.0e-20 + (L + shape * d)),\n" +
                    "          step(0.0, d));\n" +
                    "}\n" +
                    "vec3 luv2rgb(vec3 c)\n" +
                    "{\n" +
                    "    float L = reverseLight(c.x);\n" +
                    "    float U = c.y;\n" +
                    "    float V = c.z;\n" +
                    "    float lim = chromaLimit(atan(V, U), L);\n" +
                    "    float len = length(vec2(U,V));\n" +
                    "    if(len > lim) {\n" +
                    "      lim /= len;\n" +
                    "      U *= lim;\n" +
                    "      V *= lim;\n" +
                    "    }\n" +
                    "    if (L <= 0.0001) {\n" +
                    "        return vec3(0.0);\n" +
                    "    } else if(L >= 0.9999) {\n" +
                    "        return vec3(1.0);\n" +
                    "    } else {\n" +
                    "      if (L <= 0.08) {\n" +
                    "        c.y = L / kappa;\n" +
                    "      } else {\n" +
                    "        c.y = (L + 0.16) / 1.16;\n" +
                    "        c.y *= c.y * c.y;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    float iL = 1. / (13.0 * L);\n" +
                    "    float varU = U * iL + refUV.x;\n" +
                    "    float varV = V * iL + refUV.y;\n" +
                    "    c.x = 2.25 * varU * c.y / varV;\n" +
                    "    c.z = (3. / varV - 5.) * c.y - (c.x / 3.);\n" +
                    "    vec3 rgb = c * m;\n" +
                    "    return rgb;\n" +
                    "}\n" +
                    "void main()\n" +
                    "{\n" +
                    "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "  vec3 luv = rgb2luv(linear(tgt.rgb));\n" +
                    "  luv.x = forwardLight(clamp(luv.x + v_color.x - 0.5372549, 0.0, 1.0));\n" +
                    "  luv.yz = (luv.yz) + (v_color.yz);\n" +
                    "  gl_FragColor = vec4(sRGB(clamp(luv2rgb(luv), 0.0, 1.0)), v_color.a * tgt.a);\n" +
                    "}";
}
