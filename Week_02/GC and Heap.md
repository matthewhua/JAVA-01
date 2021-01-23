### GC and Heap

### 1. ParallelGC

> 吞吐量最大
>
> 在合适的不影响服务使用的情况下,使用paralleclGC 效果最好
>
> 设置内存大时,吞吐率反而降低

### 2. CMS GC

> 采用并发模式的标记清除的GC
>
> JDK10 之后被G1 替代

### 3. G1 GC

>采用块状分区, 不以新生代与老年代 为顺序分界内存地址, 延迟较低
>
>在内存较大时,使用G1 GC 优势比较大



### 4. ZGC & ShennandoahGC

>(ZGC)通过着色指针和读屏障，实现几乎全部的并发执行，几 
>
>毫秒级别的延迟，线性可扩展； 
>
>两种GC 方式非常相似
>
>GC延迟非常低(ZGC可以在10ms 之内,但ShennandoahGC不保证)

### 5. Summmary

>1. 相同堆大小的情况下，平均Young GC的时间: Serial GC > Parallel GC > CMS GC > G1 GC(ZGC 和 Shennandoah GC还没有测试)
>2. 堆越大，分配给Young区的空间越大，YGC的频率越小，但是YGC STW停顿时间越长