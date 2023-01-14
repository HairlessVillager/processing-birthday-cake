PShape getCandy() {
    float a = 0.5;
    PShape sh = createShape();
    
    sh.beginShape(TRIANGLES);
    sh.noStroke();
    sh.colorMode(HSB, 1.0);
    sh.fill(random(1.0), 1.0, 1.0);
    
    sh.vertex(0.0, -a, -a);
    sh.vertex(0.0, +a, -a);
    sh.vertex(0.0, -a, +a);

    sh.vertex(0.0, +a, -a);
    sh.vertex(0.0, -a, +a);
    sh.vertex(0.0, +a, +a);

    sh.endShape();
    return sh;
}
