# def canAchieveMax(a, m):
#     avail = 0
#     for el in a:
#         if el <= m:
#             # we have (m - el) extra space to transfer
#             # from elements to the right of this one
#             avail += m - el
#         elif (el - avail) <= m:
#             # this element exceeds our maximum value,
#             # but we can transfer as much as we need
#             # to elements to the left
#             avail -= el - m
#         else:
#             # this element exceeds our maximum value
#             # and if we were to transfer it to any
#             # number of elements to the left, they
#             # would exceed it instead. 
#             # we definitely cannnot achieve this max
#             return False

#     return True

# def minimizeMax(a):
#     i, j = 0, max(a)
#     while i < j - 1:
#         m = (i + j) // 2

#         if canAchieveMax(a, m):
#             # we can achieve a max of m, so no need to
#             # try to achieve a greater max. let's see if we 
#             # can achieve a lower, more optimal max
#             j = m
#         else:
#             # we can't achieve m or any max below m, so try
#             # to find a greater max that we can achieve
#             i = m + 1

#     # we can either achieve i or j, try i first since it's smaller
#     return i if canAchieveMax(a, i) else j
import math


def solution(arr):
    # idea is that you can keep shift value from right to left, but not left to right,
    # this means you should minimize your left most node, s.t. the maximum of the array is minimized
    # a.k.a your left most node should always be the maximum

    available_sink = 0
    current_max = arr[0]
    for i in range(1, len(arr)):
        a = arr[i]
        if a < current_max:
            available_sink += current_max - a
        else:
            diff = a - current_max
            if diff <= available_sink:
                available_sink -= diff
            else:
                # we yield, we need to distributed the remain across all members
                remain = diff - available_sink
                count = i+1
                current_max += math.ceil(remain / count)
                available_sink = 0 if remain % count == 0 else count - remain % count
    return current_max


print(solution([10, 3, 5, 7]))
tests = [[1,5,7,6],[5,15,19],[10,3,5,7]]
# for t in tests:
#     print(t, minimizeMax(t))
t1=[1,5,7,6]
t2=[4 ,10, 3 ,5, 7]
t3=[10,3,5,7]

print(minimizeMax(t2))