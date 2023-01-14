PShape getCandle() {
    float step = 0.01 * TWO_PI;
    float r = 0.8;
    float h = 20.0;
    PShape sh = createShape();
    
    sh.beginShape(TRIANGLES);
    sh.noStroke();
    sh.colorMode(HSB, 1.0);
    sh.fill(random(1.0), 0.5, 1.0);
    for(float ang = 0.0; ang < TWO_PI; ang += step) {
        sh.vertex(0.0, 0.0, 0.0);
        sh.vertex(r * cos(ang), 0.0, r * sin(ang));
        sh.vertex(r * cos(ang + step), 0.0, r * sin(ang + step));

        sh.vertex(0.0, h, 0.0);
        sh.vertex(r * cos(ang), h, r * sin(ang));
        sh.vertex(r * cos(ang + step), h, r * sin(ang + step));
        
        sh.vertex(r * cos(ang), 0.0, r * sin(ang));
        sh.vertex(r * cos(ang + step), 0.0, r * sin(ang + step));
        sh.vertex(r * cos(ang), h, r * sin(ang));

        sh.vertex(r * cos(ang + step), 0.0, r * sin(ang + step));
        sh.vertex(r * cos(ang), h, r * sin(ang));
        sh.vertex(r * cos(ang + step), h, r * sin(ang + step));
    }
    sh.endShape();
    return sh;
}
