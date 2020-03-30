##day 01
    base knowledge of thread
    a. OnlyMain
    b. StartAndRun--difference between start() and run()
    c. UseThreadLocal-- how to use thread local
    d. NewThread -- how to create a thread
    e. EndRunnable -- how to end a runnable thread
    f. EndThread -- how to end a thread
    g. HasInterruptException -- the method that throws InterruptedException
    h. syn -- synchronize key word
    i. vola -- volatile key word
    j. wn - wait and notify/notifyAll
    k. pool-- wait timeout
    l. UseJoin -- use the join()

    yield() -- will not release lock
    sleep() -- will not release lock
    wait()  -- get the lock first, then release lock, get the lock again after return from wait() method
    notify() -- get the lock first, then release lock

##day 02
    a. SumArray -- use fork join to calculate the sum of the array
    b. FindDirsFiles -- fork join asynchronous
    c. UseFuture -- use future task
    d. tools-- how to use concurrent utils
       UseCountDownLatch
       UseCyclicBarrier
       UseExchanger
       semaphore

##day 03
    #CAS
    a.AtomicInt
    b.AtomicReference
    c.AtomicArray
    d.AtomicStampedReference

    CAS problem:

    1.ABA problem, use version number to solve, like optimistic lock
    2.cost a lot of cpu
    3.atomic operation for only one share variable

    #Lock
    difference between Synchronized and Lock:
        Synchronized: simple.
        Lock:can be interrupted, time out control, try to get a lock

    ReentrantLock
    FairLock: like FIFO, first come, first service
    UnFairLock: not FIFO

    UnFairLock is more efficient, make full use of the time cost from suspended() to ready()

    Exclusive lock and ReadWriteLock

    Condition + Lock == wait/notify/notifyAll + synchronize

    readwrite -- how to use readwriteLock
    condition -- how to use condition

##day 04
    #AQS (AbstractQueuedSynchronizer) Template Design Pattern

        ##Template method:
        Exclusive acquisition:
            accquire()
            acquireInterruptibly()
            tryAcquireNanos()
        Shared access:
            acquireShared()
            acquireSharedInterruptibly()
            tryAcquireSharedNanos()
        Exclusive release lock:
            release()
        Shared release lock:
            releaseShared()

        ##Process methods that require subclass coverage
        Exclusive acquisition:
            tryAcquire()
        Exclusive release:
            tryRelease()
        Shared access:
            tryAcquireShared()
        Shared release:
            tryReleaseShared()
        Whether this synchronizer is in exclusive mode:
            isHeldExclusively()

        ## Node class:
        Threads that fail to compete will be packaged into a Node and placed in a synchronous queue.

            CANCELLED 1:
            The thread waited for a timeout or was interrupted and needs to be removed from the queue

            SIGNAL -1:
            the subsequent node wait state, the current node, and the subsequent nodes are notified to run

            CONDITION -2:
            the current node is in the waiting queue

            PROPAGATE -3:
            shared, indicating that the state is to be propagated to the subsequent nodes

            0: indicating the initial state

        ## ConditionObject class:
            maintain the waiting list
            await()
            signal()
    LockSupport
        1.block a thread
        2.wake up a thread
        3.create basic synchronous tool
        park()
        unpark()

    MyLock -- implements my Lock class by AQS
    TrinityLock -- shared lock

##day 05
    # concurrent container

    ConcurrentHashMap:

    before JDK 1.7:
        Segments
            Segment:
                [table]
                    [HashEntry]



    after JDK1.8:
        LinkedList
        RedBlackTree



    HashTable (implements Map Interface):
        HashTable uses synchronized to ensure thread safety, but HashTable is very inefficient in the case of fierce thread
        competition. Because when one thread accesses the synchronization method of HashTable, other threads also enter the
        blocking or polling state when they also access the synchronization method of HashTable. For example, if thread 1
        uses put() to add elements, thread 2 not only cannot use put() to add elements, but also cannot use get() to obtain
        elements, so the more intense the competition, the lower the efficiency.


    bitwise -- bit operation
    
    ConcurrentSkipListMap
    
    ConcurrentLinkedQueue
    
    CopyOnWriteArrayList
    
    CopyOnWriteArraySet
        implements by CopyOnWriteArrayList
    
    
    #blocking queue
    blockqueue -- how to use the DelayQueue
    ArrayBlockingQueue:
    A bounded blocking queue consisting of an array structure. Request an initial size based on the FIFO principle
    
    LinkedBlockingQueue:
    A bounded blocking queue consisting of a linked list structure. According to the FIFO principle, the initial size 
    may not be set. Integer.Max_Value
    
    The difference between ArrayBlockingQueue and LinkedBlockingQueue:
    a. Lock: ArrayBlockingQueue has only one lock, LinkedBlockingQueue uses two locks to improve the read and write performance
    b. Implementation: ArrayBlockingQueue inserts elements directly LinkedBlockingQueue needs to be converted.
    
    PriorityBlockingQueue:
    An unbounded blocking queue that supports prioritization. By default, in the natural order, either implement the 
    compareTo () method and specify the constructor parameter Comparator
    
    DelayQueue: An unbounded blocking queue implemented using a priority queue. The blocking queue that supports delayed
     acquisition of elements must implement the Delayed interface. Application scenario: Implement your own cache system,
      order expiry, time-limited payment, etc.
    
    SynchronousQueue:
    A blocking queue that does not store elements. Every put operation has to wait for a take operation
    
    LinkedTransferQueue:
    An unbounded blocking queue consisting of a linked list structure.
    transfer (), the method must return after the consumer has consumed it, and tryTransfer () returns immediately 
    regardless of whether the consumer receives it.
    
    LinkedBlockingDeque:
    A two-way blocking queue consisting of a linked list structure. You can insert and remove elements from both 
    the head and the tail of the queue to achieve work secrets. The method name takes First to operate on the head 
    and last to operate from the tail.

##day 06
    TheadPool    
    -- reduce the create time and destroy time every time we use a thread
    
    mypool-- how to create my simple thread pool   
    schedule -- how to use ScheduledThreadPoolExecutor
    completion -- how to use CompletionService
    
    Base class:
    ThreadPoolExecutor
    
    FixedThreadPool
    SingleThreadExecutor
    CachedThreadPool: use SynchronousQueue
    WorkStealingPool: base on forkJoin Pool
    ScheduledThreadPoolExecutor
    
    CompletionService
    
##day 07
    thread safety
    
    problems that thread unsafety will cost:
        dead lock
        
        Dynamic deadlock
        
            solution:guarantee the lock order
        
        thread hungery
            the thread with low priority won't get the cpu resource
            
        
        
    performance -- how to imporve performance
    
    singleinstance -- the use of single instance

## day 08
    demo1 -- project 1
    demo2 -- project 2
        single thread->thread pool-> thread pool+cache
        performance 1:15:40      
        
    HashMap 20%-40% memory  utilization rate
    ConcurrentHashMap 10%-25% of HashMap
    
    how to utilize the performance?
    base on business, locate the bottle neck, then decide the solution.

## day 09
    JMM (Java Memory Model)    
        Shared Variable
            variable visability
            thread race
            
        Instruction Reorder     
       
        Dependency
            Data Dependency
                Write then Read
                    a=1;
                    b=a;
                                
                Write then Write
                    a=1;
                    a=2;
                                
                Read then Write
                    a=b;
                    b=1;
                    
            Control Dependency
                as-if-serial
                       
    
    Cache Consistency Protocol
    
    How to solve concurrency problem?
        Memory Barrier! 
        1. disable reordering 2. Force flush out memory
        types:
            a. LoadLoadBarriers
            b.StoreStoreBarriers
            c.LoadStoreBarriers
            d.StoreLoadBarriers
        example:
            volatile field
            final field
            
        Critical Section (add lock)
    
    Happens-Before
    
    Volatile
    implements by cpu's lock_pre instruction
    
    synchronized
    implements by monitorenter and monitorexit instructions
    
    bia-lock
        always get by one thread
        
    light-lock
        CAS
    
    heavy-lock
        blocking, response late