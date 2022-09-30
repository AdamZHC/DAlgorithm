package basic.list;

/**
 * 使用泛型，比Object类更安全
 * @param <T>
 */
public interface  MyList<T> {
    //在接口中不能写成员变量
    /**
     *  ------ CURD ------
     *  关于方法的说明：
     *      可以对下面的CURD操作在接口中进行方法重载 ---实现不同的功能
     *      1.add :
     *          params: -- val -->尾插法
     *                 -- idx, val -->索引插法
     *      2.delete:
     *          params: -- idx -->按照索引删除
     *                  -- void --> 去头
     *      3.update:
     *          params: -- idx -->单点修改
     *      4.query
     *          params: -- idx -->单点查询
     * @param val
     */
    void add(T val) throws RuntimeException;

    /**
     *
     * @param val 待修改值
     * @param idx 索引
     */
    void add(T val, int idx);

    /**
     *
     * @param idx 索引
     */
    void delete(int idx);

    void delete();
    /**
     *
     * @param val 待修改值
     * @param idx 索引
     */
    void update(int idx , T val);

    /**
     *
     * @param idx 索引
     */
    T query(int idx);

    /**
     * 顺序表是否为空，是否满
     * @return
     */
    boolean isEmpty();
    boolean isFull();
    int size();
}
