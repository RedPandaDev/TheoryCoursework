# sample graph implemented as a dictionary
graph = {'Ax': {'Bz':"a", 'Ay':"b"},
         'Ay': {'Bz':"a", 'Az':"b"},
         'Az': {'By':"a", 'Az':"b"},
         'Bx': {'Az':"a", 'By':"b"},
         'By': {'Az':"a", 'Bz':"b"},
         'Bz': {'Ay':"a", 'Bz':"b"}}

transitions = []
# visits all the nodes of a graph (connected component) using BFS
def bfs_connected_component(graph, start):
    # keep track of all visited nodes
    explored = []
    # keep track of nodes to be checked
    queue = [start]

    levels = {}         # this dict keeps track of levels
    levels[start]= 0    # depth of start node is 0

    visited= [start]     # to avoid inserting the same node twice into the queue

    # keep looping until there are nodes still to be checked
    while queue:
       # pop shallowest node (first node) from queue
        node = queue.pop(0)
        explored.append(node)
        neighbours = graph[node]
        #print(neighbours)

        # add neighbours of node to queue
        for neighbour in neighbours:
            if neighbour not in visited:
                queue.append(neighbour)
                visited.append(neighbour)
                transitions.append(graph[node][neighbour])

                levels[neighbour]= levels[node]+1
                if (neighbour== "Ay"):
                    return explored

                # print(neighbour, ">>", levels[neighbour])


    
    return explored

bfs_connected_component(graph,'Ax') # returns ['A', 'B', 'C', 'E', 'D', 'F', 'G']
