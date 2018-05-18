var continuer = true;
var x = 10;
var y = 1;

while (continuer) {
    if (x == 0) {
	continuer = false;
    } else {
	y = y * x;
	x = x - 1;
    }
}

x;
y;
