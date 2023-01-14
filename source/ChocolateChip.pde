PShape getChocolate() {
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
    PShape chocolate = createShape();

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
    chocolate.beginShape(TRIANGLES);
    chocolate.noStroke();
    chocolate.fill(#40382E);

    for (int i = 0; i < 12 * 3; i++) {
        chocolate.vertex(vertexs[faces[i]].x, vertexs[faces[i]].y, vertexs[faces[i]].z);
    }

    chocolate.endShape();
    return chocolate;
}
