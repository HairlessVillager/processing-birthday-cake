/* autogenerated by Processing revision 1289 on 2023-01-12 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import processing.sound.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class birthday extends PApplet {

 //<>//

PShape cake;
SoundFile file;

float light = 0.0f;
float h1 = 20.0f;
float r1 = 30.0f;
float h2 = 16.0f;
float r2 = 20.0f;

public void addMainBody() {
    cake.addChild(getMainBody());
}

public void addCream() {
    // floor down
    int n1 = 40;
    for (int i = 0; i < n1; i++) {
        PShape cream = getCream();
        cream.scale(0.02f);
        cream.translate(r1 - 2.0f, h1, 0.0f);
        cream.rotateY(i * TWO_PI / n1);
        cake.addChild(cream);
    }
    // floor up
    int n2 = PApplet.parseInt(n1 / r1 * r2);
    for (int i = 0; i < n2; i++) {
        PShape cream = getCream();
        cream.rotateY(PI);
        cream.scale(0.02f);
        cream.translate(r2 - 2.0f, h1 + h2, 0.0f);
        cream.rotateY(- i * TWO_PI / n2);
        cake.addChild(cream);
    }
}

public void addChocolates() {
    float x = 0.25f;
    float y = 15.0f;
    float z = 10.0f;
    // floor down
    int n1 = 20;
    for (int i = 0; i < n1; i++) {
        PShape chocolate = getChocolate();
        chocolate.scale(x, y, z);
        chocolate.rotateZ(0.02f);
        chocolate.rotateY(0.2f);
        chocolate.translate(r1 + 0.3f, y / 2.0f, 0.0f);
        chocolate.rotateY(i * TWO_PI / n1);
        cake.addChild(chocolate);
    }
}

public void addFruits() {
    int n1 = 25;
    int n2 = 40;

    // floor down
    for (int i = 0; i < n1; i++) {
        PShape fruit = getFruit();
        fruit.rotateY(random(TWO_PI));
        fruit.translate((r1 + r2) / 2.0f + random(-3.0f, 0.0f), h1 - 2.0f, 0.0f);
        fruit.rotateY(i * TWO_PI / n1);
        cake.addChild(fruit);
    }
    // floor up
    while (n2-- > 0) {
        float x;
        float y;
        do {
            x = random(-r2, r2);
            y = random(-r2, r2);
        } while (sqrt(sq(x) + sq(y)) > r2 - 5.0f);
        PShape fruit = getFruit();
        fruit.rotateY(random(TWO_PI));
        fruit.translate(x, h1 + h2 - 2.0f, y);
        cake.addChild(fruit);
    }
}

public void addCandles() {
    int n = 18;
    while (n-- > 0) {
        float x;
        float y;
        do {
            x = random(-r2, r2);
            y = random(-r2, r2);
        } while (sqrt(sq(x) + sq(y)) > r2 - 3.0f);
        PShape candle = getCandle();
        candle.translate(x, h1 + h2, y);
        cake.addChild(candle);
    }
}

public void addCandies() {
    int n = 100;
    while (n-- > 0) {
        float lim = 0.5f;
        float ang = random(TWO_PI);
        float h = random(h2);
        PShape candy = getCandy();

        candy.rotateX(random(-lim, lim));
        candy.translate(r2, h1 + h, 0.0f);
        candy.rotateY(ang);
        cake.addChild(candy);
    }
}

public void setup() {
    //size(1920, 1080, P3D);
    /* size commented out by preprocessor */;
    /* smooth commented out by preprocessor */;

    cake = createShape(GROUP);
    addMainBody();
    addCream();
    addChocolates();
    addFruits();
    addCandles();
    addCandies();
    cake.scale(10.0f);

    file = new SoundFile(this, "Happy Birthday.mp3");
    file.play();
}

public void draw() {
    if (light < 1.0f) {
        light += 0.005f;
    }

    background(255 * light);

    ambientLight(31 * light, 31 * light, 31 * light);
    pointLight(255 * light, 255 * light, 255 * light, 1000.0f * cos(frameCount * 0.01f), 1000.0f, 1000.0f * sin(frameCount * 0.01f));
    pointLight(255 * light, 255 * light, 255 * light, 1000.0f * cos(frameCount * 0.01f + TWO_PI / 3.0f), 1000.0f, 1000.0f * sin(frameCount * 0.01f + TWO_PI / 3.0f));
    pointLight(255 * light, 255 * light, 255 * light, 1000.0f * cos(frameCount * 0.01f - TWO_PI / 3.0f), 1000.0f, 1000.0f * sin(frameCount * 0.01f - TWO_PI / 3.0f));

    shape(cake);
    camera(
        800.0f * cos(map(mouseX, 0.0f, width, 0.0f, TWO_PI)),
        mouseY * 10.0f,
        800.0f * sin(map(mouseX, 0.0f, width, 0.0f, TWO_PI)),
        0.0f, 200.0f, 0.0f, 0.0f, -1.0f, 0.0f);
}

public void mousePressed() {
    exit();
}
public PShape getCandle() {
    float step = 0.01f * TWO_PI;
    float r = 0.8f;
    float h = 20.0f;
    PShape sh = createShape();
    
    sh.beginShape(TRIANGLES);
    sh.noStroke();
    sh.colorMode(HSB, 1.0f);
    sh.fill(random(1.0f), 0.5f, 1.0f);
    for(float ang = 0.0f; ang < TWO_PI; ang += step) {
        sh.vertex(0.0f, 0.0f, 0.0f);
        sh.vertex(r * cos(ang), 0.0f, r * sin(ang));
        sh.vertex(r * cos(ang + step), 0.0f, r * sin(ang + step));

        sh.vertex(0.0f, h, 0.0f);
        sh.vertex(r * cos(ang), h, r * sin(ang));
        sh.vertex(r * cos(ang + step), h, r * sin(ang + step));
        
        sh.vertex(r * cos(ang), 0.0f, r * sin(ang));
        sh.vertex(r * cos(ang + step), 0.0f, r * sin(ang + step));
        sh.vertex(r * cos(ang), h, r * sin(ang));

        sh.vertex(r * cos(ang + step), 0.0f, r * sin(ang + step));
        sh.vertex(r * cos(ang), h, r * sin(ang));
        sh.vertex(r * cos(ang + step), h, r * sin(ang + step));
    }
    sh.endShape();
    return sh;
}
public PShape getCandy() {
    float a = 0.5f;
    PShape sh = createShape();
    
    sh.beginShape(TRIANGLES);
    sh.noStroke();
    sh.colorMode(HSB, 1.0f);
    sh.fill(random(1.0f), 1.0f, 1.0f);
    
    sh.vertex(0.0f, -a, -a);
    sh.vertex(0.0f, +a, -a);
    sh.vertex(0.0f, -a, +a);

    sh.vertex(0.0f, +a, -a);
    sh.vertex(0.0f, -a, +a);
    sh.vertex(0.0f, +a, +a);

    sh.endShape();
    return sh;
}
public PShape getChocolate() {
    float[] xyz = {1.0f, 1.0f, 1.0f};
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
        xyz[i] /= 2.0f;
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
    chocolate.fill(0xFF40382E);

    for (int i = 0; i < 12 * 3; i++) {
        chocolate.vertex(vertexs[faces[i]].x, vertexs[faces[i]].y, vertexs[faces[i]].z);
    }

    chocolate.endShape();
    return chocolate;
}
public PVector cream(float u, float v) {
    float d = 70.0f;

    float r = 0.4f * sin(map(u, 0.0f, 1.0f, 0.0f, 16 * PI)) + 1.0f;
    PVector vec = new PVector(
        d * cos(map(u, 0.0f, 1.0f, 0.0f, TWO_PI)) * (1.0f - v) * r,
        d * v * 2.0f,
        d * sin(map(u, 0.0f, 1.0f, 0.0f, TWO_PI)) * (1.0f - v) * r
        );
    float x = vec.x;
    float y = (vec.z + d) * sin(map(vec.y, 0.0f, d, 0.0f, PI / 2));
    float z = (vec.z + d) * cos(map(vec.y, 0.0f, d, 0.0f, PI / 2)) - d;
    return vec.set(x, y, z);
}

public PShape getCream() {
    float step = 0.05f;
    float esp = 0.0f;
    PShape cream = createShape();

    cream.beginShape(TRIANGLES);
    cream.fill(0xFFE2BAC3);
    for (float u = 0.0f; u < 1.0f - esp; u += step) {
        for (float v = 0.0f; v < 1.0f - esp; v += step) {
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
float[] strawberry_coef = {
    -0.0872851f,
    3.2027538f,
    -5.1820064f,
    1.1036716f,
    2.8340156f,
    -9.257555f,
    20.097197f,
    -19.405933f,
    8.061499f,
    -2.3792388f};
    
public float func(float x, float[] c) {
    float res = 0.0f;
    float a = 1.0f;
    for (int i = 0; i < c.length; i++) {
        res += c[i] * a;
        a *= x;
    }
    return res;
}

public PShape getStrawberry() {
    float step = 0.02f;
    float esp = 1e-3f;
    int leave = 7;
    float k = 0.7f;
    float m = 0.1f;
    float n = 0.23f;
    float lim = 0.15f;
    PShape sh = createShape();
    sh.beginShape(TRIANGLES);
    sh.noStroke();
    sh.fill(0xFFFF0000);
    for(float u = 0.0f; u < 1.0f - esp; u += step) {
        for(float v = 0.031f; v < 0.83f - esp; v += step) {
            float r = func(v, strawberry_coef) * k;
            float r2 = func(v + step, strawberry_coef) * k;
            sh.vertex(r * cos(map(u, 0.0f, 1.0f, 0.0f, TWO_PI)), v, r * sin(map(u, 0.0f, 1.0f, 0.0f, TWO_PI)));
            sh.vertex(r * cos(map(u + step, 0.0f, 1.0f, 0.0f, TWO_PI)), v, r * sin(map(u + step, 0.0f, 1.0f, 0.0f, TWO_PI)));
            sh.vertex(r2 * cos(map(u, 0.0f, 1.0f, 0.0f, TWO_PI)), v + step, r2 * sin(map(u, 0.0f, 1.0f, 0.0f, TWO_PI)));

            sh.vertex(r * cos(map(u + step, 0.0f, 1.0f, 0.0f, TWO_PI)), v, r * sin(map(u + step, 0.0f, 1.0f, 0.0f, TWO_PI)));
            sh.vertex(r2 * cos(map(u, 0.0f, 1.0f, 0.0f, TWO_PI)), v + step, r2 * sin(map(u, 0.0f, 1.0f, 0.0f, TWO_PI)));
            sh.vertex(r2 * cos(map(u + step, 0.0f, 1.0f, 0.0f, TWO_PI)), v + step, r2 * sin(map(u + step, 0.0f, 1.0f, 0.0f, TWO_PI)));
        }
    }
    sh.fill(0xFF00FF00);
    for(int i = 0; i < leave; i++) {
        sh.vertex(n * cos(map(i, 0, leave, 0, TWO_PI)), random(lim), n * sin(map(i, 0, leave, 0, TWO_PI)));
        sh.vertex(m * -sin(map(i, 0, leave, 0, TWO_PI)), 0.0f, m * cos(map(i, 0, leave, 0, TWO_PI)));
        sh.vertex(m * sin(map(i, 0, leave, 0, TWO_PI)), 0.0f, m * -cos(map(i, 0, leave, 0, TWO_PI)));
    }
    sh.endShape();
    sh.rotateX(PI);
    sh.translate(0.0f, 1.0f, 0.0f);
    sh.scale(10.0f);
    return sh;
}

public PShape getBlueberry() {
    float step = 0.02f;
    PShape sh = createShape();
    
    sh.beginShape(TRIANGLES);
    sh.noStroke();
    sh.fill(0xFF677EB4);
    for(float u = 0.0f; u < 1.0f; u += step) {
        for(float v = 0.0f; v < 1.0f; v += step) {
            float uu = map(u, 0.0f, 1.0f, 0.0f, TWO_PI);
            float vv = map(v, 0.0f, 1.0f, 0.0f, PI);
            float uus = map(u + step, 0.0f, 1.0f, 0.0f, TWO_PI);
            float vvs = map(v + step, 0.0f, 1.0f, 0.0f, PI);

            sh.vertex(cos(uu) * sin(vv), cos(vv), sin(uu) * sin(vv));
            sh.vertex(cos(uus) * sin(vv), cos(vv), sin(uus) * sin(vv));
            sh.vertex(cos(uu) * sin(vvs), cos(vvs), sin(uu) * sin(vvs));

            sh.vertex(cos(uus) * sin(vv), cos(vv), sin(uus) * sin(vv));
            sh.vertex(cos(uu) * sin(vvs), cos(vvs), sin(uu) * sin(vvs));
            sh.vertex(cos(uus) * sin(vvs), cos(vvs), sin(uus) * sin(vvs));
        }
    }
    sh.endShape();
    sh.scale(2.0f);
    return sh;
}

public PShape getKiwifruit() {
    float[] xyz = {1.0f, 1.0f, 1.0f};
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
        xyz[i] /= 2.0f;
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
    sh.fill(0xFFE9D032);

    for (int i = 0; i < 12 * 3; i++) {
        sh.vertex(vertexs[faces[i]].x, vertexs[faces[i]].y, vertexs[faces[i]].z);
    }

    sh.endShape();
    sh.scale(3.0f);
    return sh;
}

public PShape getFruit() {
    PShape sh;
    int type = PApplet.parseInt(random(3.0f));
    
    switch(type) {
        case 0 :
            sh = getStrawberry();
            break;
        case 1 :
            sh = getBlueberry();
            sh.translate(0.0f, 3.0f, 0.0f);
            break;
        case 2 :
            sh = getKiwifruit();
            sh.translate(0.0f, 3.0f, 0.0f);
            break;
        default :
            sh = getStrawberry();
    }
    return sh;
}
public PShape getMainBody() {
    float step = PI * 0.002f;
    int col = 0xFFFFFFFF;
    PShape mainBody = createShape();

    mainBody.beginShape(TRIANGLES);
    mainBody.noStroke();
    mainBody.fill(col);

    // floor down
    for (float ang = 0.0f; ang < TWO_PI; ang += step) {
        mainBody.vertex(r1 * cos(ang), 0.0f, r1 * sin(ang));
        mainBody.vertex(r1 * cos(ang), h1, r1 * sin(ang));
        mainBody.vertex(r1 * cos(ang + step), 0.0f, r1 * sin(ang + step));

        mainBody.vertex(r1 * cos(ang), h1, r1 * sin(ang));
        mainBody.vertex(r1 * cos(ang + step), 0.0f, r1 * sin(ang + step));
        mainBody.vertex(r1 * cos(ang + step), h1, r1 * sin(ang + step));

        mainBody.vertex(r1 * cos(ang), h1, r1 * sin(ang));
        mainBody.vertex(r1 * cos(ang + step), h1, r1 * sin(ang + step));
        mainBody.vertex(0.0f, h1, 0.0f);
    }

    // floor up
    for (float ang = 0.0f; ang < TWO_PI; ang += step) {
        mainBody.vertex(r2 * cos(ang), h1, r2 * sin(ang));
        mainBody.vertex(r2 * cos(ang), h1 + h2, r2 * sin(ang));
        mainBody.vertex(r2 * cos(ang + step), h1, r2 * sin(ang + step));

        mainBody.vertex(r2 * cos(ang), h1 + h2, r2 * sin(ang));
        mainBody.vertex(r2 * cos(ang + step), h1, r2 * sin(ang + step));
        mainBody.vertex(r2 * cos(ang + step), h1 + h2, r2 * sin(ang + step));

        mainBody.vertex(r2 * cos(ang), h1 + h2, r2 * sin(ang));
        mainBody.vertex(r2 * cos(ang + step), h1 + h2, r2 * sin(ang + step));
        mainBody.vertex(0.0f, h1 + h2, 0.0f);
    }

    mainBody.endShape();

    return mainBody;
}


    public void settings() { fullScreen(P3D);
smooth(8); }

    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "birthday" };
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}