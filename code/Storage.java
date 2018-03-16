
class Storage//class used fot storing the date of the hashmap
{
  String key;
  String value;
  int hashcode;
  Storage nextentry;

  public Storage(String key, String value, int hashcode, Storage nextentry)
  {
    this.key=key;
    this.value=value;
    this.hashcode=hashcode;
    this.nextentry=nextentry;
  }
}
