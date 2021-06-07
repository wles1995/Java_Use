import sun.misc.Unsafe;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.*;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.LinkPermission;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleToIntFunction;
import static java.lang.Math.*;


public class sand {

    public static void main(String[] args) throws InterruptedException {
        father f = new son();
        System.out.println(f.i);
    }
    static class father{
        int i = 20;
        public father(){
            print();
            i = 30;
        }
        public void print(){
            System.out.println("father:" + i);
        }
    }
    static class son extends father{
        int i = 10;
        public son(){
            this.print();
            i = 15;
        }
        public void print(){
            System.out.println("son:" + i);
        }
    }


    public static int[][] way50(int[][] array){
        if(array == null){
            System.out.println("输入错误");
            return null;
        }
        int row = array.length;
        int col = array[0].length;
        for (int i = 1; i < row-1; i++) {
            if(col !=array[i].length )
                System.out.println("输入非图像");
                return null;
        }
        int temp;
        //翻转
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j < col/2 ; j++) {
                temp = array[i][j];
                array[i][j] = array[i][col-j-1];
                array[i][col-j-1] = temp;
            }
        }

        //反转
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j < col ; j++) {
                array[i][j] = array[i][j]==1?0:1;
                System.out.print(array[i][j]);
            }
            System.out.println();
        }

        return array;
    }
    public static int way51(int[] array){
        int flag = 0;
        int max = 0;
        int size = 0;
        int size1 = 0;
        for (int i = 0; i < array.length-1; i++) {
            if(array[i]>array[i+1]){
                flag = 1;
                max = i;
                size = size + size1 + 1;
                size1 = 0;
            }
            if(flag == 1 && array[max]>array[i])
                size++;
            if(flag == 1 && array[max]<array[i])
                size1++;
        }
        if(flag == 1 && array[max]>array[array.length-1])
            size++;
        return size;
    }
    public static void way1() throws ExecutionException, InterruptedException {//callable
        FutureTask<String> futureTask = new FutureTask<String>(()->{
            String str = "";
            for (int i = 0; i < 10; i++) {
                str = "第"+ i + "次";
            }
            return str;});
        Thread my = new Thread(futureTask);
        my.start();
        System.out.println(futureTask.get());
    }
    public static void way2() throws InterruptedException {  //线程强制执行
        Thread mainThread = Thread.currentThread();
        Thread joinThread = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                try {
                    Thread.sleep(100);
//                    if(i>20)
//                        mainThread.join();
                    System.out.println(Thread.currentThread().getName() +" - " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "线程休眠");
        joinThread.start();
        mainThread.join();
        for (int i = 0; i < 60; i++) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +" - " + i);
        }
    }
    public static void way3() throws InterruptedException {  //线程礼让
        Thread mainThread = Thread.currentThread();
        Thread joinThread = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                try {
                    Thread.sleep(200);
                    if(i%2==0)
                        mainThread.yield();
                    System.out.println(Thread.currentThread().getName() +" - " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "线程休眠");
        joinThread.start();
        for (int i = 0; i < 60; i++) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +" - " + i);
        }
    }
    public static void way4(){
        Runnable ticketboy = new MyTread2();
        Thread t1 = new Thread(ticketboy,"票贩子-"+1);
        Thread t2 = new Thread(ticketboy,"票贩子-"+2);
        Thread t3 = new Thread(ticketboy,"票贩子-"+3);
        t1.start();t2.start();t3.start();

    }
    public static void way5(){   //消费者与生产者
        massage m = new massage();
        new Thread(new ProducerThread(m)).start();
        new Thread(new ConductorThread(m)).start();

    }
    public static void way6() throws InterruptedException {  //守护线程 class Daemon
        Daemon d = new Daemon();
        new Thread(d).start();
        Thread.sleep(2000);
    }
    public static void way7() throws InterruptedException {   //StringBuilder（线程不安全） 和 StringBuffer（线程安全） 的区别
        StringBuilder buffer = new StringBuilder(30);
        for (int i = 0; i < 20; i++) {
            new Thread(()-> {
                buffer.append(Thread.currentThread().getName() + "\n");
            },  "线程对象-" + i).start();
        }
        Thread.sleep(1000);
        System.out.println(buffer);
        System.out.println(Thread.currentThread().getContextClassLoader());
        StringBuffer buffer1 = new StringBuffer(30);
        for (int i = 0; i < 20; i++) {
            new Thread(()-> {
                buffer1.append(Thread.currentThread().getName() + "\n");
            },  "线程对象-" + i).start();
        }
        Thread.sleep(1000);
        System.out.println(buffer1);
    }
    public static void way8(){   //出现异常之后可以使用autocloseable接口
        try(NetMessage m = new NetMessage()){
            m.send("我的");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void way9() throws InterruptedException, IOException { //Runtime类
        Runtime runtime = Runtime.getRuntime();
//        Process process = runtime.exec("calc.exe");
//        Thread.sleep(2000);
//        process.destroy();
        System.out.println("maxmemory = " + runtime.maxMemory()/1024/1024);
        System.out.println("totalmemory = " + runtime.totalMemory()/1024/1024);
        System.out.println("freememory = " + runtime.freeMemory()/1024/1024);
        runtime.gc();
    }
    public static void way10(String[] args){
        if (args.length != 2){
            System.out.println("错误");
            System.exit(1);
        }
        String message = args[0];
        int count = Integer.parseInt(args[1]);
        for (int i = 0; i < count; i++) {
            System.out.println(message);
        }
    }
    public static void way11() throws CloneNotSupportedException {   //cloneable 使用
        Mathbook math = new Mathbook("wkdk");
        Book book = new Book("wles",100, math);
        System.out.println(book);
        System.out.println(book.getM());
        Book book1 = (Book) book.clone();
        System.out.println(book1);
        System.out.println(book1.getM());
    }
    public static void way12(){
        BigDecimal b1 = new BigDecimal(12.463134563145);
        BigDecimal b2 = new BigDecimal(1.4542131363145);
        System.out.println(b1.divide(b2, 1,RoundingMode.HALF_UP));
        System.out.println(b1.divide(b2, 1,RoundingMode.HALF_DOWN));
        System.out.println(b1.divide(b2, 1,RoundingMode.HALF_EVEN));

    }
    public static void way13() throws ParseException {
        Date date = new Date();  //时间转String
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String str = format.format(date);
        System.out.println(str);

        String string = "2021-03-15 12:27:09:497";  //String 转时间
        Date date1 = format.parse(string);
        System.out.println(date1);

        LocalDate d1 = LocalDate.now();
        LocalTime d2 = LocalTime.now();
        LocalDateTime d3 = LocalDateTime.now();
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
        System.out.println(String.format("当前日期：%s-%s-%s", d1.getYear(),d1.getMonth(),d1.getDayOfMonth()));
        System.out.println("获取一周的时间数" + d1.getDayOfWeek().getValue());
        System.out.println("今天所处年的天数" + d1.getDayOfYear());
        System.out.println("今天所处月的周数" + d1.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
        System.out.println("今天所处年的周数" + d1.get(ChronoField.ALIGNED_WEEK_OF_YEAR));

        LocalDate localDate = LocalDate.parse("1988-10-15");
        System.out.println("闰年判断：" + localDate.isLeapYear());
        System.out.println("所在的一周时间数：" + localDate.getDayOfWeek());
        System.out.println("所在月的第一天：" + localDate.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("所在月的第一天：" + localDate.withDayOfMonth(1));
        System.out.println("所在月的最后一天：" + localDate.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("三百年后的今天：" + localDate.plusYears(300));
        System.out.println("日期所处月的第一个周一：" + localDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        System.out.println("日期所处年的第一个周一：" + LocalDate.parse("1988-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));

    }
    public static void way14() {  //多线程字符串转换日期才会出现问题
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ZoneId zoneId = ZoneId.systemDefault();
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                LocalDate localDate = LocalDate.parse("1998-02-01", formatter);
                Instant instant = localDate.atStartOfDay().atZone(zoneId).toInstant();
                Date date = Date.from(instant);
                System.out.println(Thread.currentThread().getName() + " " + date);
            }).start();
        }
    }
    public static void way15(){   //Locale 类
        Locale loc = new Locale("zh", "CN");
        System.out.println(loc);
        Locale loc1 = Locale.getDefault();
        System.out.println(loc1);
    }
    public static void way16(){
        DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getInstance();
        numberFormat.applyPattern("#####,###,####.000"); //保留三位小数
        numberFormat.setRoundingMode(RoundingMode.HALF_DOWN);  //不进位
        numberFormat.setPositivePrefix("当年的收入流水");
        numberFormat.setMinimumFractionDigits(5);  //修改保留为
        System.out.println(numberFormat.format((28634134.4563434)));

        NumberFormat numberFormat1 = NumberFormat.getPercentInstance();
        System.out.println(numberFormat1.format(0.9896574));
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat1;
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        decimalFormat.setMinimumFractionDigits(2); // 保留5位小数
        System.out.println(decimalFormat.format(0.9896874));

        NumberFormat numberFormat2 = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println(numberFormat2.format(4564.45));

    }
    public static void way17(){
        String message = "wles1995";
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodeData = encoder.encode(message.getBytes());
        System.out.println(new String(encodeData));

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodeData = decoder.decode(encodeData);
        System.out.println(new String(decodeData));
        System.out.println("--------------------------------");
        String str = PasswordUtil.encrypt("wles");
        System.out.println(str);
        System.out.println(PasswordUtil.decrypt(str));
    }
    public static void way18(){   // ThreadLocal
        String[] value = new String[]{
                "wles:1995",
                "sdnia:4521",
                "fbnej:8786"
        };
        for (String s : value) {
            new Thread(()->{
                Resource.MESSAGES.set(new Message());
                Resource.MESSAGES.get().setMessage(s);
                MessagePrint.print();

            }).start();
        }
    }
    public static void way19(){  //定时任务
        Timer timer = new Timer();
        timer.schedule(new TaskThread(),1000,2000);
    }
    /**********************************************************/
    public static void way20() throws IOException {
        File file = new File("E:"+ File.separator + "Users.txt");
        if(file.exists()){
            System.out.println("文件权限："+ "可读："+ file.canRead() +"\t可写："+ file.canWrite() +"\t可执行：" + file.canExecute());
            System.out.println("文件绝对路径：" + file.getAbsolutePath());
            System.out.println("文件名称：" + file.getName());
            System.out.println("文件目录：" + file.getParent());
        }else{
            System.out.println("创建文件：" + file.createNewFile());
        }
    }
    public static void way21(){  //文件名称
        File file = new File("E:\\音乐");
        if(file.exists() && file.isDirectory()){
            System.out.println("list()方法执行结果：" + Arrays.toString(file. list()));  //子路径名称
            System.out.println("listFiles()方法执行结果" + Arrays.toString(file.listFiles()));  //将子路径转化为file对象实例
        }
    }
    public static void way22(File file){  //文件条件查找
        if(file.isDirectory()){
            File list[] = file.listFiles((f)->f.isDirectory() ? true:f.getName().endsWith(".txt"));
            if(list != null)
                for (File file1 : list) {
                    way22(file1);
                }
        }else{
            System.out.println(file);
        }
    }
    public static void way23(){
        File oldFile = new File("H:" + File.separator + "XX");
        File newFile = new File("E:" + File.separator + "XX");
        System.out.println("文件更名迁移：" + oldFile.renameTo(newFile));

    }
    public static void way24(){   //FileOutputStream
        File file = new File("E:" + File.separator + "Users.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        OutputStream output = null;
        try {
            output = new FileOutputStream(file,true);
            String message = "www.yougei.com\r\n";
            byte data[] = message.getBytes();
            output.write(data);
            //流属于宝贵的资源，操作完成后一定要进行关闭
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //通过Flie类定义一个要操作文件的路径
        //通过字节流或者字符流的子类为父类对象实例化
        //实现数据的读、写
        //流属于宝贵的资源，操作完成后一定要进行关闭


    }
    public static void way25(){
        File file = new File("E:" + File.separator + "Users.txt");
        StringBuffer stringBuffer = new StringBuffer();
        if(file.exists()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                byte data[] = new byte[8];
                int len = 0;
                while ((len = inputStream.read(data))!= -1){
                    stringBuffer.append(new String(data,0,len));
                }
                inputStream.close();
                System.out.println("读取结果：" + stringBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void way26(){
        File file = new File("E:" + File.separator + "Users.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try{
            Writer writer = new FileWriter(file);
            writer.write("dauisnd\r\n");
            writer.append("sdaiosd");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void way27(){
        File file = new File("E:" + File.separator + "Users.txt");
        if(file.exists()){
            try(Reader reader = new FileReader(file)){
                char c[] = new char[1024];
                int len = reader.read(c);
                System.out.println(new String(c,0,len));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public static void way28() throws IOException {
        File file = new File("E:" + File.separator + "Users.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("cvds\r\n");
        writer.append("sdasdgdfgdf");
        writer.flush();
        writer.close();

        Reader reader = new InputStreamReader(new FileInputStream(file));
        char []c = new char[2];
        int len = 0;
        while ((len= reader.read(c)) != -1)
            System.out.print(new String(c,0,len));
        reader.close();

    }
    public static void way29() throws IOException {
        String str = "wles";
        byte []b = new byte[1024];
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        int len = 0;
        while((len = inputStream.read(b)) !=-1){
            String s = new String(b).toUpperCase();
            outputStream.write(s.getBytes());
        }
        System.out.println(outputStream);
        inputStream.close();
        outputStream.close();
    }
    public static void way30() throws IOException { //ByteArrayOutputStream
        File file = new File("E:" + File.separator + "Users.txt");
        if(file.exists()){
            InputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte data[] = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(data)) != -1)
                outputStream.write(data,0,len);
            String content = new String(outputStream.toByteArray());
            System.out.println(content);
            inputStream.close();
            outputStream.close();
        }
    }
    public static void way31() throws IOException {
        SendThread send = new SendThread();
        ReceiveThread receive = new ReceiveThread();
//        send.getOutput().connect(receive.getInput());
        receive.getInput().connect(send.getOutput());
        new Thread(send).start();
        new Thread(receive).start();
    }
    public static void way32() throws IOException   {  //文件拷贝
        System.out.println(new CopyUtil("E:\\Users.txt","E:\\wles.txt").copy());
    }
    public static void way33() throws IOException {
//        System.getProperties().list(System.out);
        File file = new File("E:" + File.separator + "Users.txt");
        OutputStream out = new FileOutputStream(file);
        out.write("你妈波".getBytes("GBK"));
        out.close();
    }
    public static void way34() throws IOException {
        File file = new File("E:" + File.separator + "Users.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        RandomAccessFile raf  = new RandomAccessFile(file,"rw");
        String names[] = new String[]{"Zhansan","lisi","wanwu","zaoliu","sunqi"};
        int age[] = new int[]{12,13,14,15,16};
        for (int i = 0; i < names.length; i++) {
            String name = addEscpae(names[i]);
            raf.write(name.getBytes());
            raf.writeInt(age[i]);
        }
        raf.close();
    }
    public static String addEscpae(String val){
        StringBuffer buff = new StringBuffer(val);
        while(buff.length() < 7){
            buff.append(" ");
        }
        return buff.toString();
    }
    public static void way35() throws IOException {
        File file = new File("E:" + File.separator + "Users.txt");
        RandomAccessFile raf  = new RandomAccessFile(file,"r");
        raf.skipBytes(11);
        byte data[] = new byte[7];
        raf.read(data);
        int age = raf.readInt();
        System.out.println("读取数据：" + new String(data) + age);
        System.out.println("-----------------------------------------------");
        raf.seek(0);
        raf.read(data);
        age = raf.readInt();
        System.out.println("读取数据：" + new String(data) + age);
        System.out.println("-----------------------------------------------");
        raf.skipBytes(3*11);
        raf.read(data);
        age = raf.readInt();
        System.out.println("读取数据：" + new String(data) + age);
        raf.close();
    }
    public static void way36() throws FileNotFoundException {
        File file = new File("E:" + File.separator + "Users.txt");
        PrintWriter pu = new PrintWriter(new FileOutputStream(file));
        pu.println("dansldnka");
        int age = 18;
        double score = 99.5;
        String name = "wles";
        pu.printf("姓名：%s \t年龄:%d \t成绩:%5.2f", name, age, score);
        pu.close();
    }
    public static void way37() throws IOException {
        InputStream input = System.in;
        System.out.println("输入信息：");
        byte data[] = new byte[10];
        int len = input.read(data);
        System.out.println("数据回显：" + new String(data,0,len));
        input.close();
    }
    public static void way38() throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入信息");
        String str = buff.readLine();
        System.out.println("数据回显：" + str);
        buff.close();
        String s = null;
        buff = new BufferedReader(new FileReader(new File("E:" + File.separator + "Users.txt")));
        System.out.println("输入信息");
        while((str = buff.readLine()) != null)
            System.out.println("数据回显：" + str);
        buff.close();

    }
    public static void way39() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("发送信息：");
        if(scanner.hasNext()){
            String val = scanner.next();
            System.out.println("消息回显：" + val);
        }

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("发送数值：");
        if(scanner1.hasNextInt()){
            int value = scanner1.nextInt();
            System.out.println("回显数值：" + value);
        }else {
            System.err.println("输入不是数值");
        }

        Scanner scanner2 = new Scanner(new File("E:" + File.separator + "Users.txt"));
        scanner2.useDelimiter("\n");
        while (scanner2.hasNext()){
            String value = scanner2.next();
            System.out.println(value);
        }

    }
    public static void way40() throws IOException, ClassNotFoundException {
        Books b = new Books("星期五","lisd",25);
        File file = new File("E:" + File.separator + "Users.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(b);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Books d = (Books)ois.readObject();
        System.out.println(d.toString());
        ois.close();
    }
    /**********************************************************/
    public static void way41() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Books b = new Books("sd","121",35.2);
        //class类对象的实例化
        System.out.println(b.getClass());
        System.out.println(Books.class);
        System.out.println(Class.forName("Books"));

        Class<?> bookClazz = Class.forName("Books");
        //实例化方法
        System.out.println(bookClazz.newInstance().toString());
        System.out.println(bookClazz.getDeclaredConstructor().newInstance().toString());
        //动态工厂设计模式
        IBook ibook = Factory.getInstance("ProgramBook");
        ibook.read();

    }
    public static void way42(){
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                Singleton s = Singleton.getInstance();
                System.out.println(s);
            },"单例操作线程 - " + i).start();
        }
    }
    public static void way43() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("Room");  //getConstructors 与 getDeclaredConstructors使用
        System.out.println("--------------------getConstructors()获取构造方法--------------------");
        for(Constructor<?> constructor : clazz.getConstructors()){
            System.out.println("构造方法：" + constructor);
        }
        System.out.println("--------------------getDeclaredConstructors()获取构造方法--------------------");
        for(Constructor<?> constructor : clazz.getDeclaredConstructors()){
            System.out.println("构造方法：" + constructor.getTypeParameters());

        }
    }
    public static void way44() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("Room");  //获取构造方法并使用
        Constructor<?> constructors = clazz.getDeclaredConstructor(String.class, int.class, double.class);
        Room r = (Room) constructors.newInstance("sd",12,56.0);
        System.out.println(r);

    }
    public static void way45() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("Room");  //打印类的所有方法
        Method methods[] = clazz.getMethods();
        for (Method method : methods) {
            System.out.print(Modifier.toString(method.getModifiers()) + " ");
            System.out.print(method.getGenericReturnType().getTypeName() + " ");
            System.out.print(method.getName() + "(");
            Type[] parameterTypes = method.getGenericParameterTypes();
            for (Type parameterType : parameterTypes) {
                System.out.print(parameterType.getTypeName() + ",");
            }
            System.out.print(")");
            Type[] exceptions = method.getExceptionTypes();
            if(exceptions.length>0){
                System.out.print(" throws ");
                for (int i = 0; i < exceptions.length; i++) {
                    System.out.println(exceptions[i].getTypeName());
                    if(i<exceptions.length - 1){
                        System.out.print(", ");
                    }
                }
            }
            System.out.println( );
        }

    }
    public static void way46() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String name = "Name";   //反射调用方法
        String namevalue = "你好陌生人";
        Class<?> clazz = Class.forName("Room");
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Method setmethod = clazz.getMethod("set" + name, String.class);
        setmethod.invoke(obj,namevalue);
        Method getmethod = clazz.getMethod("get" + name);
        String str = (String) getmethod.invoke(obj);
        System.out.println(str);

    }
    public static void way47() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> clazz = Class.forName("Room");
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Field titleField = clazz.getDeclaredField("name");
        titleField.setAccessible(true);
        titleField.set(obj, "你好");
        System.out.println(titleField.get(obj));
    }
    public static void way48() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe"); //利用Unsafe类的对象实例可以直接绕过JVM运行机制，从而直接实现指定类的方法调用，并且实例化对象的操作都可以省略
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        Singleton s = (Singleton)unsafe.allocateInstance(Singleton.class);
        System.out.println(s.toString());


    }
    public static void way49(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("1","value1");
        map.put("2","value2");
        map.put("3","value3");
        
        //第一种：普遍使用，由于二次取值,效率会比第二种和第三种慢一倍
        System.out.println("通过Map.keySet遍历key和value：");
        for(String key : map.keySet()){
            System.out.println("key="+key+" and value="+map.get(key));
        }
        
        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Set<Map.Entry<String ,String >>set = map.entrySet();  //将map转化为Set集合，其中Map.Entry是Set中每个数据的节点
        Iterator<Map.Entry<String,String>>it=set.iterator();
        while(it.hasNext()){
            Map.Entry<String,String>entry=it.next();
            System.out.println("key="+entry.getKey()+" and value="+entry.getValue());
        }
        
        //第三种：无法在for循环时实现remove等操作
        System.out.println("通过Map.entrySet遍历key和value");
        for(Map.Entry<String,String>entry:map.entrySet()){
            System.out.println("key="+entry.getKey()+" and value="+entry.getValue());
        }
        
        //第四种：只能获取values,不能获取key
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for(String v:map.values()){
            System.out.println("value="+v);
        }

    }

}


class MyTread1 implements  Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
class MyTread implements Callable<String> {

    @Override
    public String call() throws Exception {
        String str = "";
        for (int i = 0; i < 10; i++) {
            str = "第"+ i + "次";
        }
        return str;
    }
}
class MyTread2 implements Runnable{
    private int ticket = 10;

    @Override
    public void run() {
        while (true) {
            synchronized (this){
                if(ticket > 0){
                    try {
                         Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "剩余票数：" + (--ticket));
                }else{
                    break;
                }
            }
        }
    }
}
/*********************************************************/
class massage{
    private String title;
    private String content;
    private boolean flag = true;
    public synchronized void set(String title, String content){
        if(this.flag == false){
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.title = title;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.content = content;
        this.flag = false;
        super.notify();
    }
    public synchronized String get() {
        if(this.flag == true){
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.flag = true;
        super.notify();
        return "消费者获得" + this.title + "用来" + this.content;
    }

}
class ProducerThread implements Runnable{
    private massage m;

    public ProducerThread(massage m) {
        this.m = m;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if(i % 2 == 0){
                this.m.set("汉堡","吃饭");
            }else {
                this.m.set("洗发水","洗澡");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ConductorThread implements Runnable{
    private massage m;

    public ConductorThread(massage m) {
        this.m = m;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(m.get());
        }
    }
}
/*********************************************************/
class Daemon implements Runnable{
    public Daemon() {
        Thread daemonThread = new Thread(()->{
            for (int i = 0; i < 30000; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("守护线程");
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("输出信息----------------");
        }
    }
}
/*********************************************************/
interface IMessage extends AutoCloseable{
    public void send(String msg);
}
class NetMessage implements IMessage{
    public NetMessage() {
        System.out.println("连接");
    }

    @Override
    public void send(String msg) {
        if(msg.contains("wles")){
            throw new RuntimeException("不能出现");
        }else{
            System.out.println("发送成功");
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("关闭");
    }
}
/*********************************************************/
class Book implements Cloneable{
    private String name;
    private int num;
    private Mathbook m;

    public Book(String name, int num, Mathbook m) {
        this.name = name;
        this.num = num;
        this.m = m;
    }

    public Mathbook getM() {
        return m;
    }

    public void setM(Mathbook m) {
        this.m = m;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Book book = (Book) super.clone();
        book.setM((Mathbook) this.m.clone());
        return book;
    }
}
class Mathbook implements Cloneable{
    private String dept;

    public Mathbook(String dept) {
        this.dept = dept;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
/*********************************************************/
class PasswordUtil{
    private static final int REPEAT = 5;
    private static final String SALT = "wles1995";
    private PasswordUtil(){}
    public static String encrypt(String str){
        String encodeData = SALT + str;
        Base64.Encoder encoder = Base64.getEncoder();
        for (int i = 0; i < REPEAT; i++) {
            encodeData = encoder.encodeToString(encodeData.getBytes());
        }
        return encodeData;
    }
    public static String decrypt(String str){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] data = str.getBytes();
        for (int i = 0; i < REPEAT; i++) {
            data = decoder.decode(data);
        }
        String str1 = new String(data);
        return str1.substring(SALT.length());
    }
}
/*********************************************************/
class Message{
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
class MessagePrint{
    public static void print(){
        System.out.println("发送信息：" + Resource.MESSAGES.get().getMessage());
    }
}
class Resource{
    public static final ThreadLocal<Message> MESSAGES = new ThreadLocal<>();
}
/*********************************************************/
class TaskThread extends TimerTask{

    @Override
    public void run() {
        System.out.println("定时任务：" + "wles");
    }
}
/*********************************************************/
class CopyUtil{
    private File inFile;
    private File outFile;

    public CopyUtil(String args[]){
        if(args.length !=2){
            System.out.println("输入错误");
            System.exit(1);
        }
        this.inFile = new File(args[0]);
        this.outFile = new File(args[1]);
    }
    public CopyUtil(String inPath, String outPath) {
        this.inFile = new File(inPath);
        this.outFile = new File(outPath);
    }
    public long copy() throws IOException {
        long start = System.currentTimeMillis();
        InputStream input = null;
        OutputStream output = null;
        try{
            input = new FileInputStream(inFile);
            output = new FileOutputStream(outFile);
            input.transferTo(output);
//            byte b[] = new byte[1024];
//            int len = 0;
//            while((len = input.read(b)) != -1)
//                output.write(b,0,len);
        }catch (IOException e){
            e.printStackTrace();
        }

        return System.currentTimeMillis() - start;
    }
}
/*********************************************************/
class SendThread implements Runnable{
    private PipedOutputStream pipedOutputStream = new PipedOutputStream();
    @Override
    public void run() {
        try {
            this.pipedOutputStream.write("你好".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PipedOutputStream getOutput(){
        return pipedOutputStream;
    }
}
class ReceiveThread implements Runnable{
    private PipedInputStream pipedInputStream = new PipedInputStream();
    @Override
    public void run() {
        try {
            byte data[] = new byte[1024];
            int len = this.pipedInputStream.read(data);
            System.out.println("消息读取：" + new String(data,0,len));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PipedInputStream getInput(){
        return pipedInputStream;
    }
}
/*********************************************************/
class Books implements Serializable{
    private String title;
    private transient String author;
    private double price;

    public Books() {
    }

    public Books(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Books{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
/*********************************************************/
interface IBook{
    public void read();
}
class MathBook implements IBook{

    @Override
    public void read() {
        System.out.println("学习数学");
    }
}
class ProgramBook implements IBook{

    @Override
    public void read() {
        System.out.println("学习编程");
    }
}
class Factory{
    private Factory(){}
    public static IBook getInstance(String className){
        try {
            Object ob =  Class.forName(className).getDeclaredConstructor().newInstance();
            if(ob instanceof IBook)
                return (IBook)ob;
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
/*********************************************************/
class Singleton{
    private static Singleton Instance;
    private Singleton(){
        System.out.println(Thread.currentThread().getName() +  "产生实例化对象");
    }

    @Override
    public String toString() {
        return "方法调用";
    }
    public static Singleton getInstance(){
        if(Instance == null){
            synchronized (Singleton.class){
                if(Instance == null) {
                    Instance = new Singleton();
                }
            }
        }
        return Instance;
    }

}
/*********************************************************/
class Room{
    private String name;
    private int num;
    private double fee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Room(String name, int num, double fee) {
        this.name = name;
        this.num = num;
        this.fee = fee;
    }

    public Room(String name) {
        this.name = name;
    }

    public Room(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public Room() {
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", fee=" + fee +
                '}';
    }
}
/*********************************************************/
class Outer {
    int a = 1;    // 实例变量
    static int b = 2;    // 静态变量
    class Inner {
       // Outer o = new Outer();
        int a2 = a;    // 访问实例变量
        int b2 = b;    // 访问静态变量
        public void print(){
            System.out.println(b2);
            System.out.println(a2);
        }
    }
}
/*********************************************************/
