
class WordStoreImp implements WordStore
{
  private CustomHashMap hashmap;

  public WordStoreImp(int initialStrings) // initialises an new variable hashmap, which will store the word
  {
    hashmap = new CustomHashMap(initialStrings);
  }
  public void add(String word) //adds the word to the hashmap
  {
    hashmap.add(word, word);
  }
  public int count(String word)//returns the count of the hashmap
  {
    return hashmap.count(word);
  }
  public void remove(String word)//removes the word form the hashmap
  {
    hashmap.remove(word);
  }

}
