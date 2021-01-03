package pe.com.jorgeberrios.crud.service;
import java.util.List;

public interface GenericService<T,K> {
    public T findByCode(K code);
    public List<T> findAll();
    public boolean create(T c);
    public boolean save(T c);    
    public void delete(K code);
}
