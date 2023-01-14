PVector cream(float u, float v) {
    float d = 70.0;

    float r = 0.4 * sin(map(u, 0.0, 1.0, 0.0, 16 * PI)) + 1.0;
    PVector vec = new PVector(
        d * cos(map(u, 0.0, 1.0, 0.0, TWO_PI)) * (1.0 - v) * r,
        d * v * 2.0,
        d * sin(map(u, 0.0, 1.0, 0.0, TWO_PI)) * (1.0 - v) * r
        );
    float x = vec.x;
    float y = (vec.z + d) * sin(map(vec.y, 0.0, d, 0.0, PI / 2));
    float z = (vec.z + d) * cos(map(vec.y, 0.0, d, 0.0, PI / 2)) - d;
    return vec.set(x, y, z);
}

PShape getCream() {
    float step = 0.05;
    float esp = 0.0;
    PShape cream = createShape();

    cream.beginShape(TRIANGLES);
    cream.fill(#E2BAC3);
    for (float u = 0.0; u < 1.0 - esp; u += step) {
        for (float v = 0.0; v < 1.0 - esp; v += step) {
            PVector v1 = cream(u, v);
            PVector v2 = cream(u + step, v);
            PVector v3 = cream(u, v + step);
            PVector v4 = cream(u + step, v + step);

            cream.vertex(v1.x, v1.y, v1.z);
            cream.vertex(v2.x, v2.y, v2.z);
            cream.vertex(v3.x, v3.y, v3.z);

            cream.vertex(v2.x, v2.y, v2.z);
            cream.vertex(v3.x, v3.y, v3.z);
            cream.vertex(v4.x, v4.y, v4.z);
        }
    }
    cream.endShape();
    return cream;
}
