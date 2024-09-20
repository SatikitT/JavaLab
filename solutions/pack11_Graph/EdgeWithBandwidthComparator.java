package solutions.pack11_Graph;

class EdgeWithBandwidthComparator implements java.util.Comparator<EdgeWithBandwidth> {
    @Override
    public int compare(EdgeWithBandwidth e1, EdgeWithBandwidth e2) {
        return Integer.compare(e1.getLatency(), e2.getLatency());
    }
}



