import processing.sound.*; //<>//

PShape cake;
SoundFile file;

float light = 0.0;
float h1 = 20.0;
float r1 = 30.0;
float h2 = 16.0;
float r2 = 20.0;

void addMainBody() {
    cake.addChild(getMainBody());
}

void addCream() {
    // floor down
    int n1 = 40;
    for (int i = 0; i < n1; i++) {
        PShape cream = getCream();
        cream.scale(0.02);
        cream.translate(r1 - 2.0, h1, 0.0);
        cream.rotateY(i * TWO_PI / n1);
        cake.addChild(cream);
    }
    // floor up
    int n2 = int(n1 / r1 * r2);
    for (int i = 0; i < n2; i++) {
        PShape cream = getCream();
        cream.rotateY(PI);
        cream.scale(0.02);
        cream.translate(r2 - 2.0, h1 + h2, 0.0);
        cream.rotateY(- i * TWO_PI / n2);
        cake.addChild(cream);
    }
}

void addChocolates() {
    float x = 0.25;
    float y = 15.0;
    float z = 10.0;
    // floor down
    int n1 = 20;
    for (int i = 0; i < n1; i++) {
        PShape chocolate = getChocolate();
        chocolate.scale(x, y, z);
        chocolate.rotateZ(0.02);
        chocolate.rotateY(0.2);
        chocolate.translate(r1 + 0.3, y / 2.0, 0.0);
        chocolate.rotateY(i * TWO_PI / n1);
        cake.addChild(chocolate);
    }
}

void addFruits() {
    int n1 = 25;
    int n2 = 40;

    // floor down
    for (int i = 0; i < n1; i++) {
        PShape fruit = getFruit();
        fruit.rotateY(random(TWO_PI));
        fruit.translate((r1 + r2) / 2.0 + random(-3.0, 0.0), h1 - 2.0, 0.0);
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
        } while (sqrt(sq(x) + sq(y)) > r2 - 5.0);
        PShape fruit = getFruit();
        fruit.rotateY(random(TWO_PI));
        fruit.translate(x, h1 + h2 - 2.0, y);
        cake.addChild(fruit);
    }
}

void addCandles() {
    int n = 18;
    while (n-- > 0) {
        float x;
        float y;
        do {
            x = random(-r2, r2);
            y = random(-r2, r2);
        } while (sqrt(sq(x) + sq(y)) > r2 - 3.0);
        PShape candle = getCandle();
        candle.translate(x, h1 + h2, y);
        cake.addChild(candle);
    }
}

void addCandies() {
    int n = 100;
    while (n-- > 0) {
        float lim = 0.5;
        float ang = random(TWO_PI);
        float h = random(h2);
        PShape candy = getCandy();

        candy.rotateX(random(-lim, lim));
        candy.translate(r2, h1 + h, 0.0);
        candy.rotateY(ang);
        cake.addChild(candy);
    }
}

void setup() {
    //size(1920, 1080, P3D);
    fullScreen(P3D);
    smooth(8);

    cake = createShape(GROUP);
    addMainBody();
    addCream();
    addChocolates();
    addFruits();
    addCandles();
    addCandies();
    cake.scale(10.0);

    file = new SoundFile(this, "Happy Birthday.mp3");
    file.play();
}

void draw() {
    if (light < 1.0) {
        light += 0.005;
    }

    background(255 * light);

    ambientLight(31 * light, 31 * light, 31 * light);
    pointLight(255 * light, 255 * light, 255 * light, 1000.0 * cos(frameCount * 0.01), 1000.0, 1000.0 * sin(frameCount * 0.01));
    pointLight(255 * light, 255 * light, 255 * light, 1000.0 * cos(frameCount * 0.01 + TWO_PI / 3.0), 1000.0, 1000.0 * sin(frameCount * 0.01 + TWO_PI / 3.0));
    pointLight(255 * light, 255 * light, 255 * light, 1000.0 * cos(frameCount * 0.01 - TWO_PI / 3.0), 1000.0, 1000.0 * sin(frameCount * 0.01 - TWO_PI / 3.0));

    shape(cake);
    camera(
        800.0 * cos(map(mouseX, 0.0, width, 0.0, TWO_PI)),
        mouseY * 10.0,
        800.0 * sin(map(mouseX, 0.0, width, 0.0, TWO_PI)),
        0.0, 200.0, 0.0, 0.0, -1.0, 0.0);
}

void mousePressed() {
    exit();
}
