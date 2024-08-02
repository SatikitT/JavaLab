package solutions.pack1;
import java.util.Objects;

public class Dog {

    public Breed b;
    public int weight;

    public Dog(Breed breed, int p_weight){
        b = breed;
        weight = p_weight;
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return weight == dog.weight && b == dog.b;
    }

    @Override
    public String toString(){
        return "Dog: " + b + " with Weight:" + weight +", "; 
    }

    @Override
    public int hashCode(){
        return Objects.hash(b, weight);
    }
}
