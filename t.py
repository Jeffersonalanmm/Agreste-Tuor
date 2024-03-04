def soma(a, s=0):  # s é inicializado como 0 na primeira chamada
    if a == 0:
        return s
    else:
        s += a
        return soma(a - 1, s)

numero = 5
resultado = soma(numero)
print(resultado)
