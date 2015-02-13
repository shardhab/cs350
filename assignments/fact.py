#!/usr/bin/python
import math

diff = 2 * math.factorial(21) / 30000000000
print diff
secs = 42 * math.factorial(20) / 30000000000
print secs
mn = secs / 60
hr = mn / 60
day = hr / 24
week = day / 7
year = week / 52
print year 
