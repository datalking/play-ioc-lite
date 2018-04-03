//package com.github.datalking.hello;
//
//
//@Configuration
//@ComponentScan
//public class App {
//
//    @Bean
//    MessageService mockMessageService() {
//        return new MessageService() {
//            public String getMessage() {
//                return "Hello, it's pure spring sample!";
//            }
//        };
//
//    }
//
//    public static void main(String[] args) {
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(App.class);
//        MessagePrinter printer = context.getBean(MessagePrinter.class);
//        printer.printMessage();
//    }
//}
