
class CustomHashMap
{
  private int count;
  private Storage[] entry;
  private int max;
  private float loadfactor = 0.75f;

  public CustomHashMap(int size)
  {
    int initialsize = 1;
    while(initialsize<size)
    {
      initialsize <<= 1;
    }
    entry = new Storage[initialsize];
    max = (int)(initialsize*loadfactor);
  }

  public void add(String key, String val)
  {
    int hashcode = hashCode(key);
    int value = getValue(hashcode, entry.length);
    Storage latestentry = entry[value];
    for(;latestentry!=null;latestentry=latestentry.nextentry)
    {
      if(latestentry.key.equals(key))
      {
        key = latestentry.key + "1";
        break;
      }
      entry[value] = new Storage(key, val, hashcode, entry[value]);
      if(count++>=max)
      {
        Storage[] newEntry = new Storage[2*(entry.length)];
        for(int i=0; i<entry.length;i++)
        {
          Storage k = entry[i];
          if(k!=null)
          {
            entry[i]=null;
            while(k!=null)
            {
              Storage nextentry = k.nextentry;
              int l = getValue(hashCode(k.key), newEntry.length);
              k.nextentry = newEntry[l];
              newEntry[l]=k;
              k = nextentry;
            }
          }
        }
        entry = newEntry;
        max = (int)((2*entry.length)*loadfactor);
      }
    }
  }

  public void remove(String key)
  {
    int hashcode = hashCode(key);
    int value = getValue(hashcode, entry.length);
    Storage latestentry = entry[value];
    Storage toremove = latestentry;

    while(toremove!=null)
    {
      Storage nextentry = toremove.nextentry;
      if(toremove.hashcode==hashcode)
      {
        count--;
        if(latestentry==toremove)
        {
          entry[value]=nextentry;
        }
        else
        {
          toremove.nextentry=nextentry;
        }

      }
      latestentry = toremove;
      toremove = nextentry;
    }

  }

  public int count(String key)
  {
    int count = 0;
    while(getKey(key)!=null)
    {
      count++;
      key = key + "1";
    }
    return count;
  }


  public int hashCode(String key)
  {
    int hashcode=0;
    int k;
    for(int i =0; i<key.length();i++)
    {
      k = ((int)key.charAt(i)*(31^(key.length()-(i-1))));
      hashcode = hashcode + k;
    }
    return hashcode;
  }
  public int getValue(int key, int length)
  {
    int k;
    k = (int)key%length;
    return k;
  }

  public Storage getKey(String key)
  {
    int hashcode = hashCode(key);
    Storage entrykey = entry[getValue(hashcode, entry.length)];
    while(entrykey!=null)
    {
      if(entrykey.key.equals(key))
      {
        return entrykey;
      }
      else
      {
        entrykey = entrykey.nextentry;
      }
    }
    return null;
  }


}
