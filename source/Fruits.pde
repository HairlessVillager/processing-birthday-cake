float[] strawberry_coef = {
    -0.0872851,
    3.2027538,
    -5.1820064,
    1.1036716,
    2.8340156,
    -9.257555,
    20.097197,
    -19.405933,
    8.061499,
    -2.3792388};
    
float func(float x, float[] c) {
    float res = 0.0;
    float a = 1.0;
    for (int i = 0; i < c.length; i++) {
        res += c[i] * a;
        a *= x;
    }
    return res;
}

PShape getStrawberry() {
    float step = 0.02;
    float esp = 1e-3;
    int leave = 7;
    float k = 0.7;
    float m = 0.1;
    float n = 0.23;
    float lim = 0.15;
    PShape sh = createShape();
    sh.beginShape(TRIANGLES);
    sh.noStroke();
    sh.fill(#FF0000);
    for(float u = 0.0; u < 1.0 - esp; u += step) {
        for(float v = 0.031; v < 0.83 - esp; v += step) {
            float r = func(v, strawberry_coef) * k;
            float r2 = func(v + step, strawberry_coef) * k;
            sh.vertex(r * cos(map(u, 0.0, 1.0, 0.0, TWO_PI)), v, r * sin(map(u, 0.0, 1.0, 0.0, TWO_PI)));
            sh.vertex(r * cos(map(u + step, 0.0, 1.0, 0.0, TWO_PI)), v, r * sin(map(u + step, 0.0, 1.0, 0.0, TWO_PI)));
            sh.vertex(r2 * cos(map(u, 0.0, 1.0, 0.0, TWO_PI)), v + step, r2 * sin(map(u, 0.0, 1.0, 0.0, TWO_PI)));

            sh.vertex(r * cos(map(u + step, 0.0, 1.0, 0.0, TWO_PI)), v, r * sin(map(u + step, 0.0, 1.0, 0.0, TWO_PI)));
            sh.vertex(r2 * cos(map(u, 0.0, 1.0, 0.0, TWO_PI)), v + step, r2 * sin(map(u, 0.0, 1.0, 0.0, TWO_PI)));
            sh.vertex(r2 * cos(map(u + step, 0.0, 1.0, 0.0, TWO_PI)), v + step, r2 * sin(map(u + step, 0.0, 1.0, 0.0, TWO_PI)));
        }
    }
    sh.fill(#00FF00);
    for(int i = 0; i < leave; i++) {
        sh.vertex(n * cos(map(i, 0, leave, 0, TWO_PI)), random(lim), n * sin(map(i, 0, leave, 0, TWO_PI)));
        sh.vertex(m * -sin(map(i, 0, leave, 0, TWO_PI)), 0.0, m * cos(map(i, 0, leave, 0, TWO_PI)));
        sh.vertex(m * sin(map(i, 0, leave, 0, TWO_PI)), 0.0, m * -cos(map(i, 0, leave, 0, TWO_PI)));
    }
    sh.endShape();
    sh.rotateX(PI);
    sh.translate(0.0, 1.0, 0.0);
    sh.scale(10.0);
    return sh;
}

PShape getBlueberry() {
    float step = 0.02;
    PShape sh = createShape();
    
    sh.beginShape(TRIANGLES);
    sh.noStroke();
    sh.fill(#677EB4);
    for(float u = 0.0; u < 1.0; u += step) {
        for(float v = 0.0; v < 1.0; v += step) {
            float uu = map(u, 0.0, 1.0, 0.0, TWO_PI);
            float vv = map(v, 0.0, 1.0, 0.0, PI);
            float uus = map(u + step, 0.0, 1.0, 0.0, TWO_PI);
            float vvs = map(v + step, 0.0, 1.0, 0.0, PI);

            sh.vertex(cos(uu) * sin(vv), cos(vv), sin(uu) * sin(vv));
            sh.vertex(cos(uus) * sin(vv), cos(vv), sin(uus) * sin(vv));
            sh.vertex(cos(uu) * sin(vvs), cos(vvs), sin(uu) * sin(vvs));

            sh.vertex(cos(uus) * sin(vv), cos(vv), sin(uus) * sin(vv));
            sh.vertex(cos(uu) * sin(vvs), cos(vvs), sin(uu) * sin(vvs));
            sh.vertex(cos(uus) * sin(vvs), cos(vvs), sin(uus) * sin(vvs));
        }
    }
    sh.endShape();
    sh.scale(2.0);
    return sh;
}

PShape getKiwifruit() {
    float[] xyz = {1.0, 1.0, 1.0};
    PVector[] vertexs = new PVector[8];
    int[] faces = {
        0, 1, 2,
        1, 2, 3,
        4, 5, 6,
        5, 6, 7,
        0, 1, 4,
        1, 4, 5,
        2, 3, 6,
        3, 6, 7,
        1, 3, 5,
        3, 5, 7,
        0, 2, 4,
        2, 4, 6
    };
    int[] ones = {-1, +1};
    PShape sh = createShape();

    for (int i = 0; i < 3; i++) {
        xyz[i] /= 2.0;
    }
    for (int i : ones) {
        for (int j : ones) {
            for (int k : ones) {
                vertexs[(i + 1) / 2 * 4 + (j + 1)  / 2 * 2 + (k + 1) / 2] = new PVector(i * xyz[0], j * xyz[1], k * xyz[2]);
            }
        }
    }
    sh.beginShape(TRIANGLES);
    sh.noStroke();
    sh.fill(#E9D032);

    for (int i = 0; i < 12 * 3; i++) {
        sh.vertex(vertexs[faces[i]].x, vertexs[faces[i]].y, vertexs[faces[i]].z);
    }

    sh.endShape();
    sh.scale(3.0);
    return sh;
}

PShape getFruit() {
    PShape sh;
    int type = int(random(3.0));
    
    switch(type) {
        case 0 :
            sh = getStrawberry();
            break;
        case 1 :
            sh = getBlueberry();
            sh.translate(0.0, 3.0, 0.0);
            break;
        case 2 :
            sh = getKiwifruit();
            sh.translate(0.0, 3.0, 0.0);
            break;
        default :
            sh = getStrawberry();
    }
    return sh;
}
