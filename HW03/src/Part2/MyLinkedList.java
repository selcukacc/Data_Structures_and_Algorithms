package Part2;
import java.util.LinkedList;
import java.util.ListIterator;

/** Hw3 Part2
 * @author Islam Goktan Selcuk
 * Number: 141044071
 * */
public class MyLinkedList<E> extends LinkedList {
    // engellenen elemanlari tutar
    private LinkedList<E> disabledElements = new LinkedList<>();
    // engellenen elemanlarin listesini tutar
    private LinkedList<Integer> indexesOfElements = new LinkedList<>();

    /**
     * Engellenen elemani tekrar aktif hale getirir.
     * @param element Aktif hale getirilmek istenen eleman.
     */
    public void enable(E element) {
        // disableElement listesindeki index
        int disIndex = disabledElements.indexOf(element);
        // asil listedeki index
        int index = findIndexOfDisabled(indexesOfElements.get(disIndex));
        // eleman aktif duruma getirildigi icin disable listesi icin kullanilan
        // indexesOfElements listesinden de silinir.
        indexesOfElements.remove(disIndex);

        // eger verilen elemanin indexi size'dan kucukse eski index'i hesaplanarak
        // listeye geri eklenir.
        if(super.size() > index) {
            super.add(index, disabledElements.get(disIndex));
            disabledElements.remove(disIndex);
        }
        // eger size'dan buyukse listenin sonuna eklenir
        else {
            super.add(disabledElements.get(disIndex));
            disabledElements.remove(disIndex);
        }
    }

    /**
     * LinkedList'teki herhangi bir elemani engeller ve listedisi birakir.
     * @param index Engellenmek istenen elemanin indexi.
     */
    public void disable(int index) {
        // istenen eleman listeden alinir
        E element = (E)super.get(index);
        // disabledElements listesine eklenir ve indexi kaydedilir.
        disabledElements.add(element);
        indexesOfElements.add(index + disabledElements.size() - 1);
        // asil listeden silinerek eleman engellenmis olur.
        super.remove(index);
    }

    /**
     * Engellenen elemanin hangi index'e kaydedilecegini hesaplar.
     * @param indexOfElement // elemanin engellendigi sirada alindigi index
     * @return // eklenmek istenen listede olmasi gereken index.
     */
    private int findIndexOfDisabled(int indexOfElement) {
        int counter = 0;
        // engellenen elemanlardan buyukse indeksi, eklenecegi indeks azaltilir
        for(int temp : indexesOfElements)
            if(temp < indexOfElement)
                counter++;

        return indexOfElement - counter;
    }

    @Override
    public String toString() {
        return "allElements=" + super.toString() +
                "\nDisabledElements=" + disabledElements;
    }

}