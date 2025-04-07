B = [['z']]

def expr(n):
    global B
    if n == 1:
        B.append(['(zz)'])
        return 1
    
    expr(n-1)
    C = []

    for i in range(n//2 + n%2):
        for j in range(len(B[i])):
            for g in range(len(B[-1-i])):
                C.append('(' + B[i][j] + B[-1-i][g] + ')')
                if B[i][j] != B[-1-i][g] and B[i] != B[-1-i]:
                    C.append('(' + B[-1-i][g] + B[i][j] + ')')
    B.append(C)
    return

expr(int(input()))
print(B[-1])

"""
def expr(n):
    if n <= 0:
        b = 1
        return b
    if n == 1:
        b = 1
        return b
    
    b = 0
    for i in range(n//2):
        b += 2 * (expr(i) * expr(n-i-1))
    if n%2 == 1:
        b += expr(n//2) * expr(n//2)

    return b

B = [1]

def exprM(n):
    global B
    if n <= 0:
        return B[0]
    if n == 1:
        B.append(1)
        return B[1]
    
    exprM(n-1)

    b = 0
    for i in range(n//2):
        b += 2 * (B[i] * B[n-i-1])
    if n%2 == 1:
        b += B[n//2] * B[n//2]
    
    B.append(b)

    return B[n]
"""