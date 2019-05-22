public class TestGroovy {

    public getTime(Date date) {
        println('getTime')
        return date.getTime();
    }

    public getDate(long time) {
        println('getDate')
        return new Date(time);
    }
}