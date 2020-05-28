---删除命名空间---
use ApolloConfigDB;
delete from Audit where EntityName='Namespace' and EntityId in (select Id from Namespace where AppId='tms-lbs-service' and NamespaceName='url');
delete from Audit where EntityName='AppNamespace' and EntityId in (select Id from AppNamespace where AppId='tms-lbs-service' and Name='url');
delete from Audit where EntityName='Release' and EntityId in (select Id from `Release` where AppId='tms-lbs-service' and Name='url');
delete from Audit where EntityName='ReleaseHistory' and EntityId in (select Id from ReleaseHistory where AppId='tms-lbs-service' and NamespaceName='url');
delete from Audit where EntityName='Item' and EntityId in (select Id from Item where NamespaceId in (select Id from Namespace where AppId='tms-lbs-service' and NamespaceName='url'));
delete from Item where NamespaceId in (select Id from Namespace where AppId='tms-lbs-service' and NamespaceName='url');
delete from AppNamespace where AppId='tms-lbs-service' and Name='url';
delete from Commit where AppId='tms-lbs-service' and NamespaceName='url';
delete from InstanceConfig where ConfigAppId='tms-lbs-service' and ConfigNamespaceName='url';
delete from Namespace where AppId='tms-lbs-service' and NamespaceName='url';
delete from `Release` where AppId='tms-lbs-service' and NamespaceName='url';
delete from ReleaseHistory where AppId='tms-lbs-service' and NamespaceName='url';
delete from ReleaseMessage where Message like '%tms-lbs-service%url%';

---删除集群---
use ApolloConfigDB;
delete from Audit where EntityName='Cluster' and EntityId in (select Id from Cluster where AppId='tms-lbs-service' and Name='CBS-DEV');
delete from Audit where EntityName='Namespace' and EntityId in (select Id from Namespace where AppId='tms-lbs-service' and ClusterName='CBS-DEV');
delete from Audit where EntityName='Item' and EntityId in (select Id from Item where NamespaceId in (select Id from Namespace where AppId='tms-lbs-service' and ClusterName='CBS-DEV'));
delete from Audit where EntityName='Release' and EntityId in (select Id from `Release` where AppId='tms-lbs-service' and ClusterName='CBS-DEV');
delete from Audit where EntityName='ReleaseHistory' and EntityId in (select Id from ReleaseHistory where AppId='tms-lbs-service' and ClusterName='CBS-DEV');
delete from Item where NamespaceId in (select Id from Namespace where AppId='tms-lbs-service' and ClusterName='CBS-DEV');
delete from Cluster where AppId='tms-lbs-service' and Name='CBS-DEV';
delete from Commit where AppId='tms-lbs-service' and ClusterName='CBS-DEV';
delete from Instance where AppId='tms-lbs-service' and ClusterName='CBS-DEV';
delete from InstanceConfig where ConfigAppId='tms-lbs-service' and ConfigClusterName='CBS-DEV';
delete from Namespace where AppId='tms-lbs-service' and ClusterName='CBS-DEV';
delete from `Release` where AppId='tms-lbs-service' and ClusterName='CBS-DEV';
delete from ReleaseHistory where AppId='tms-lbs-service' and ClusterName='CBS-DEV';
delete from ReleaseMessage where Message like '%tms-lbs-service%CBS-DEV%';


---删除命名空间---
use ApolloPortalDB;
delete from AppNamespace where AppId='tms-lbs-service' and Name='url';
delete from UserRole where RoleId in (select Id from Role where RoleName like '%tms-lbs-service%url%');
delete from RolePermission where RoleId in (select Id from Role where RoleName like '%tms-lbs-service%url%');
delete from Role where RoleName like '%tms-lbs-service%url%';
