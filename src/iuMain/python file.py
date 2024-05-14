import sympy as sy 
import math

# Definir las constantes
cos = 1/2
sin = (math.sqrt(3))/2
N = sy.sqrt(3)
W = 1

# Definir las variables
Ax, Ay, Dy, AB, AE, EB, EF, BF, BC, FC, FG, CD, CG, GD = sy.symbols('Ax, Ay,Dy,AB,AE,EB,EF,BF,BC,FC,FG,CD,CG,GD')

# Definir las ecuaciones
eqns = [
    Ax,
    Ay + AE*sin,
    AE+W/N,
    AB - W/N*cos,
    -EB*sin+W/N*sin,
    EF + W/N*cos + W/N*cos,
    BF*sin + W/N*sin,
    BC - W*N/6 - W/N*cos - W/N*cos,
    -W + W/N*sin - FC*sin,
    FG - W/N*cos + W/N*cos + W/N,
    CG*sin - W/N*sin,
    CD - W*N/2 + W/N*cos + W/N*cos,
    -GD*sin - W/N*sin,
    W/2 - W + Dy,
    -FG-CG*cos+GD*cos,
    
]

# Resolver el sistema de ecuaciones
result = sy.solve(eqns, [Ax, Ay, Dy, AB, AE, EB, EF, BF, BC, FC, FG, CD, CG, GD])

# Definir el orden de las variables
variable_order = [Ax, Ay, Dy, AB, AE, EB, EF, BF, BC, FC, FG, CD, CG, GD]

# Imprimir los resultados en el orden definido
for var in variable_order:
    print(f"{var}: {result[var].evalf(n=4)}"+"W")