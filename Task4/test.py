graph = {
    "Ax": ["Ay", "Az"],
    "Ay": ["Ax", "Az"],
    "Az": ["Bx", "Az"],
    "Bx": ["By", "Bz"]
}


def bfs(graph_to_search, start, end):
    queue = [[start]]
    visited = set()

    while queue:
        # Gets the first path in the queue
        path = queue.pop(0)

        # Gets the last node in the path
        vertex = path[-1]
        
        # Checks if we got to the end
        if vertex == end:
            return path
        # We check if the current node is already in the visited nodes set in order not to recheck it
        elif vertex not in visited:
            # enumerate all adjacent nodes, construct a new path and push it into the queue
            for current_neighbour in graph_to_search.get(vertex, []):
                print("vertex", vertex, "neigh", current_neighbour)
                print(path)
                new_path = list(path)
                new_path.append(current_neighbour)
                queue.append(new_path)
                print(queue)

            # Mark the vertex as visited
            visited.add(vertex)



print (bfs(graph, "Ax", "Bx"))