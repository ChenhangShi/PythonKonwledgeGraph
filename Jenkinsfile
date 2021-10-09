pipeline {
  agent any

  stages {
    stage('pull code') {
      steps {
        checkout scm
//         git(url: 'http://212.129.149.40/181250124_2/backend-2.git', credentialsId: 'GitLab212.129.149.40', branch: 'feature-cloud-ypx_try', changelog: true, poll: false)
        sh 'echo build: $PROJECT_NAME, version: $PROJECT_VERSION, push to hub: $REGISTRY'
//         sh 'mvn clean install -Dmaven.test.skip=true -gs `pwd`/mvn-settings.xml'
sh 'mvn clean install -Dmaven.test.skip=true'
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
            sh 'echo "$DOCKER_PASSWORD" | docker login $REGISTRY -u "$DOCKER_USERNAME" --password-stdin'
            sh 'docker tag  $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:latest '
            sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:latest '
          }
      }
    }

    stage('deploy') {
      steps {
        sh 'docker start nacos'
        sh 'docker start sentinel'
        sh 'docker rm -f "$PROJECT_NAME"'
        sh 'docker rmi -f "$REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME"'
        sh 'docker run -d --name $PROJECT_NAME -p $PORT:8080 -v /usr/lib/java8/jdk1.8.0_261:/usr/local/jdk $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME'
      }
    }
//     stage('部署到k8s') {
//       steps {
//         input(id: "deploy-to-dev-$PROJECT_NAME", message: "是否将 $PROJECT_NAME 部署到集群中?")
//         kubernetesDeploy(configs: "$PROJECT_NAME/deploy/**", enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
//       }
//     }

    stage('release with tag'){
      when{
        expression{
          return params.PROJECT_VERSION =~ /v.*/
        }
      }
      steps {
//             input(id: 'release-image-with-tag', message: 'sure to release this version of image?')
            sh 'docker tag  $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:$PROJECT_VERSION '
            sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/$PROJECT_NAME:$PROJECT_VERSION '
            withCredentials([usernamePassword(credentialsId: "$GITLAB_CREDENTIAL_ID", passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                sh 'git config --global user.email "181250xxx@smail.nju.edu.cn" '
                sh 'git config --global user.name "GROUP2" '
                sh 'git tag -a $PROJECT_NAME-$PROJECT_VERSION -m "$PROJECT_VERSION" '
                sh 'git push http://$GIT_USERNAME:$GIT_PASSWORD@$GITLAB_URL --tags --ipv4'
            }
      }
    }
  }
  environment {
    DOCKER_CREDENTIAL_ID = 'aliyun-hub-id'
    REGISTRY = 'registry.cn-hangzhou.aliyuncs.com'
    DOCKERHUB_NAMESPACE = 'group2'
    GITLAB_CREDENTIAL_ID = 'GitLab212.129.149.40'
    GITLAB_URL = '212.129.149.40/181250124_2/backend-2.git'
    GITLAB_ACCOUNT = '181250172'
    SONAR_CREDENTIAL_ID = 'sonar-qube'
    KUBECONFIG_CREDENTIAL_ID = 'demo-kubeconfig'
  }

  parameters {
    string(name: 'PROJECT_VERSION', defaultValue: 'v0.0Beta', description: 'project version')
    string(name: 'PROJECT_NAME', defaultValue: 'cloud-gateway-gateway9001', description: 'choose module to build')
    string(name: 'PORT', defaultValue: '8080', description: 'service port')
  }
}