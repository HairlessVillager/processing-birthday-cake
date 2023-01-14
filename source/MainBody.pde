PShape getMainBody() {
    float step = PI * 0.002;
    color col = #FFFFFF;
    PShape mainBody = createShape();

    mainBody.beginShape(TRIANGLES);
    mainBody.noStroke();
    mainBody.fill(col);

    // floor down
    for (float ang = 0.0; ang < TWO_PI; ang += step) {
        mainBody.vertex(r1 * cos(ang), 0.0, r1 * sin(ang));
        mainBody.vertex(r1 * cos(ang), h1, r1 * sin(ang));
        mainBody.vertex(r1 * cos(ang + step), 0.0, r1 * sin(ang + step));

        mainBody.vertex(r1 * cos(ang), h1, r1 * sin(ang));
        mainBody.vertex(r1 * cos(ang + step), 0.0, r1 * sin(ang + step));
        mainBody.vertex(r1 * cos(ang + step), h1, r1 * sin(ang + step));

        mainBody.vertex(r1 * cos(ang), h1, r1 * sin(ang));
        mainBody.vertex(r1 * cos(ang + step), h1, r1 * sin(ang + step));
        mainBody.vertex(0.0, h1, 0.0);
    }

    // floor up
    for (float ang = 0.0; ang < TWO_PI; ang += step) {
        mainBody.vertex(r2 * cos(ang), h1, r2 * sin(ang));
        mainBody.vertex(r2 * cos(ang), h1 + h2, r2 * sin(ang));
        mainBody.vertex(r2 * cos(ang + step), h1, r2 * sin(ang + step));

        mainBody.vertex(r2 * cos(ang), h1 + h2, r2 * sin(ang));
        mainBody.vertex(r2 * cos(ang + step), h1, r2 * sin(ang + step));
        mainBody.vertex(r2 * cos(ang + step), h1 + h2, r2 * sin(ang + step));

        mainBody.vertex(r2 * cos(ang), h1 + h2, r2 * sin(ang));
        mainBody.vertex(r2 * cos(ang + step), h1 + h2, r2 * sin(ang + step));
        mainBody.vertex(0.0, h1 + h2, 0.0);
    }

    mainBody.endShape();

    return mainBody;
}
