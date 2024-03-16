def sort_edges(edges_list):
    sorted_edges_list = []
    actual_length = len(edges_list)
    while len(sorted_edges_list) < actual_length:
        minor_edge = edges_list[0]
        for i in range(1, len(edges_list)):
            if edges_list[i][2] < minor_edge[2]:
                minor_edge = edges_list[i]
        sorted_edges_list.append(minor_edge)
        edges_list.remove(minor_edge)
    return sorted_edges_list


def kruskal_mst(edges_list, nodes_quantity):
    sorted_edges_list = sort_edges(edges_list)
    nodes_list = []
    mst_weight = 0
    edges_quantity = 1
    index = 1
    nodes_list.append(sorted_edges_list[0][0])
    nodes_list.append(sorted_edges_list[0][1])
    mst_weight += sorted_edges_list[0][2]
    while (len(nodes_list) < nodes_quantity or edges_quantity < (nodes_quantity - 1)) and index < len(sorted_edges_list):
        if sorted_edges_list[index][0] not in nodes_list:
            nodes_list.append(sorted_edges_list[index][0])
            if sorted_edges_list[index][1] not in nodes_list:
                nodes_list.append(sorted_edges_list[index][1])
            mst_weight += sorted_edges_list[index][2]
            edges_quantity += 1
        elif sorted_edges_list[index][1] not in nodes_list:
            nodes_list.append(sorted_edges_list[index][0])
            if sorted_edges_list[index][0] not in nodes_list:
                nodes_list.append(sorted_edges_list[index][1])
            mst_weight += sorted_edges_list[index][2]
            edges_quantity += 1
        elif len(nodes_list) >= nodes_quantity and edges_quantity < (nodes_quantity - 1):
            mst_weight += sorted_edges_list[index][2]
            edges_quantity += 1
        index += 1
    return mst_weight


def receive_data():
    (nodes, edges) = (int(x) for x in str(input()).split())
    my_edges = []
    for i in range(edges):
        (node1, node2, weight) = (int(x) for x in str(input()).split())
        my_edges.append([node1, node2, weight])
    print(kruskal_mst(my_edges, nodes))


receive_data()