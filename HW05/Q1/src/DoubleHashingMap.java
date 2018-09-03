public class DoubleHashingMap<K, V> {

    private static class MapElement<K, V> {
        K key;
        V value;

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public MapElement(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private MapElement<K, V> [] table;

    private static int capacity = 11;

    private int totalKeys;

    private int totalDeleted;

    private final double LOAD_FACTOR = 0.75;

    private final MapElement<K, V> DELETED =
            new MapElement<>(null, null);

    public DoubleHashingMap() {
        table = new MapElement[capacity];
    }

    private int hashCode2(Object key) {
        return 7 - key.hashCode() % 7;
    }

    private int find(K key) {
        int index = key.hashCode() % table.length;
        int hash2 = hashCode2(key);

        if(index < 0)
            index += table.length;

        while(table[index] != null &&
                !key.equals(table[index].key)) {
            index += hash2;
            if(index >= table.length)
                index %= table.length;
        }
        return index;
    }

    public V get(K key) {
        int index = find(key);
        if(table[index] == null)
            return null;
        else
            return table[index].getValue();
    }

    public V put(K key, V value) {
        int index = find(key);
        if(table[index] == null) {
            table[index] = new MapElement<>(key, value);
            totalKeys++;
            double loadFactor = (double) (totalKeys + totalDeleted) / table.length;
            if(loadFactor > LOAD_FACTOR)
                rehash();
            return null;
        }

        V old = table[index].getValue();
        table[index].setValue(value);
        return old;
    }

    private void rehash() {
        MapElement<K, V> oldTable[] = table;
        table = new MapElement[2 * oldTable.length + 1];
        totalKeys = 0;
        totalDeleted = 0;
        for(int i = 0; i < oldTable.length; i++) {
            if((oldTable[i] != null) && (oldTable[i] != DELETED))
                put(oldTable[i].key, oldTable[i].value);
        }
    }

    public V remove(K key) {
        int index = find(key);
        if(table[index] == null)
            return null;
        V oldValue = table[index].value;
        table[index] = DELETED;
        totalKeys--;
        totalDeleted++;
        return oldValue;
    }

    public int size() {
        return totalKeys;
    }

    public boolean isEmpty() {
        return totalKeys == 0;
    }
}