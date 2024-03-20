Boa noite!! 

Para executar o projeto em conjunto com as APIs, favor, não esquecer de subir o projeto Java no ar (https://github.com/henriquefral/ESGfy-API) e também configurar o seu IP (localhost) no arquivo https://github.com/henriquefral/ESGfy-APP/blob/main/app/src/main/res/xml/network_security_config.xml.

<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">192.168.0.17</domain>
    </domain-config>
</network-security-config>
