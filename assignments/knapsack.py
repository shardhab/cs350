#!/usr/bin/python

outputs = [[-1 for x in range(8)] for x in range(7)]
weights = [99, 6, 5, 2, 1, 5, 3]
values = [0, 43, 35, 20, 17, 26, 52]

for i in range(8):
  outputs[0][i] = 0

for i in range(7):
  outputs[i][0] = 0

print outputs

def knapsack(i, j):
  print "i: " + str(i) + " j: " + str(j)
  print outputs[i][j]
  if outputs[i][j] < 0:
    if j < weights[i]:
      value = knapsack(i - 1, j)
    else:
      value = max(knapsack(i-1, j), values[i] + knapsack(i-1, j-weights[i]))
    outputs[i][j] = value 
  else:
    print "repeat! i: " + str(i) + " j: " + str(j)
  return outputs[i][j]

val = knapsack(6,7)
print val
print outputs
