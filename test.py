# sample graph implemented as a dictionary
# graph = {'Ax': {'Bz':"a", 'Ay':"b"},
#          'Ay': {'Bz':"a", 'Az':"b"},
#          'Az': {'By':"a", 'Az':"b"},
#          'Bx': {'Az':"a", 'By':"b"},
#          'By': {'Az':"a", 'Bz':"b"},
#          'Bz': {'Ay':"a", 'Bz':"b"}}
stateTransitions = {'Ax': set(['Bz', 'Ay']),
         'Ay': set(['Bz', 'Az']),
         'Az': set(['By', 'Az']),
         'Bx': set(['Az', 'By']),
         'By': set(['Az', 'Bz']),
         'Bz': set(['Ay', 'Bz'])}

transitions = []
# visits all the nodes of a graph (connected component) using BFS
def bfs_paths(stateTransitions, start, accept):
    queue = [(start, [start])]

    while queue:

        (vertex, path) = queue.pop(0)
        print(vertex)
        print(path)

        for next in stateTransitions[vertex] - set(path):
            if next == accept:
                print(path + [next])
                return
            else:
                queue.append((next, path + [next]))

bfs_paths(stateTransitions, 'Ax', 'Az') # ['A', 'C', 'F']