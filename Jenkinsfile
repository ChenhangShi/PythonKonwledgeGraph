pipeline {
  agent any

  stages {
    stage('pull code') {
      steps {
        git(url: 'http://212.129.149.40/181250124_2/backend-2.git', credentialsId: 'GitLab212.129.149.40', branch: 'feature-cloud-ypx_try', changelog: true, poll: false)
        sh 'echo build: $PROJECT_NAME, version: $PROJECT_VERSION, push to hub: $REGISTRY'
        sh 'mvn clean install -Dmaven.test.skip=true -gs `pwd`/mvn-settings.xml'
      }
    }
//     stage('sonar代码质量分析') {
//       steps {
//         container('maven') {
//           withCredentials([string(credentialsId: "$SONAR_CREDENTIAL_ID", variable: 'SONAR_TOKEN')]) {
//             withSonarQubeEnv('sonar') {
//               sh 'echo 当前目录 `pwd`'
//               sh "mvn sonar:sonar -gs `pwd`/mvn-settings.xml -Dsonar.branch=$BRANCH_NAME -Dsonar.login=$SONAR_TOKEN"
//             }
//
//           }
//
//           timeout(time: 1, unit: 'HOURS') {
//             waitForQualityGate true
//           }
//
//         }
//
//       }
//     }
    stage('build & push') {
      steps {
          sh 'mvn -Dmaven.test.skip=true -gs `pwd`/mvn-settings.xml clean package'
          sh 'cd $PROJECT_NAME && docker build -f Dockerfile -t $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER .'
          withCredentials([usernamePassword(passwordVariable : 'DOCKER_PASSWORD' ,usernameVariable : 'DOCKER_USERNAME' ,credentialsId : "$DOCKER_CREDENTIAL_ID" ,)]) {
            sh 'docker login $REGISTRY -u "断线offline" -p "Ypxaliyun123"'
            sh 'docker tag  $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:latest '
            sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:latest '
          }
      }
    }
//     stage('部署到k8s') {
//       steps {
//         input(id: "deploy-to-dev-$PROJECT_NAME", message: "是否将 $PROJECT_NAME 部署到集群中?")
//         kubernetesDeploy(configs: "$PROJECT_NAME/deploy/**", enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
//       }
//     }

//     stage('发布版本'){
//       when{
//         expression{
//           return params.PROJECT_VERSION =~ /v.*/
//         }
//       }
//       steps {
//           container ('maven') {
//             input(id: 'release-image-with-tag', message: '发布当前版本镜像吗?')
//             sh 'docker tag  $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:$PROJECT_VERSION '
//             sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:$PROJECT_VERSION '
//             withCredentials([usernamePassword(credentialsId: "$GITEE_CREDENTIAL_ID", passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
//                 sh 'git config --global user.email "534096094@qq.com" '
//                 sh 'git config --global user.name "leifengyang" '
//                 sh 'git tag -a $PROJECT_NAME-$PROJECT_VERSION -m "$PROJECT_VERSION" '
//                 sh 'git push http://$GIT_USERNAME:$GIT_PASSWORD@gitee.com/$GITEE_ACCOUNT/gulimall.git --tags --ipv4'
//             }
//
//         }
//       }
//     }
  }
  environment {
    DOCKER_CREDENTIAL_ID = 'aliyun-hub-id'
    GITEE_CREDENTIAL_ID = 'GitLab212.129.149.40'
    KUBECONFIG_CREDENTIAL_ID = 'demo-kubeconfig'
    REGISTRY = 'registry.cn-hangzhou.aliyuncs.com'
    DOCKERHUB_NAMESPACE = 'group_2'
    GITLAB_ACCOUNT = '181250172'
    SONAR_CREDENTIAL_ID = 'sonar-qube'
    BRANCH_NAME = 'feature-cloud-ypx_try'
  }

  parameters {
    string(name: 'PROJECT_VERSION', defaultValue: 'v0.0Beta', description: 'project version')
    string(name: 'PROJECT_NAME', defaultValue: 'cloud-gateway-gateway9001', description: 'choose module to build')
  }
}