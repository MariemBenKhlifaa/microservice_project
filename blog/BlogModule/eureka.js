const Eureka = require('eureka-js-client').Eureka;

const client = new Eureka({
    instance: {
        app: 'blog-service',
        hostName: 'localhost', // Adresse de votre service Node.js
        ipAddr: '127.0.0.1',
        port: {
            $: 3000, // Le port sur lequel votre service Node.js est en cours d'exécution
            '@enabled': true,
        },
        vipAddress: 'blog-service',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        },
    },
    eureka: {
        host: 'localhost',
        port: 8761, // Port par défaut du serveur Eureka
        servicePath: '/eureka/apps/',
    },
});

client.start();
