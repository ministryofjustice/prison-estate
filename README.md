# prison-estate

[![CircleCI](https://circleci.com/gh/ministryofjustice/prison-estate/tree/master.svg?style=svg)](https://circleci.com/gh/ministryofjustice/prison-estate)
[![Known Vulnerabilities](https://snyk.io/test/github/ministryofjustice/prison-estate/badge.svg)](https://snyk.io/test/github/ministryofjustice/prison-estate)

Self-contained fat-jar micro-service to publish mappings between prisons and GP Prescribe Codes
 
### Building

```bash
./gradlew build
```

### Running

```bash
./gradlew bootRun
```

#### Health

- `/health/ping`: will respond with status `UP` to all requests.  This should be used by dependent systems to check connectivity to prison-estate,
rather than calling the `/health` endpoint.
- `/health`: provides information about the application health and its dependencies.  This should only be used
by prison-estate health monitoring (e.g. pager duty) and not other systems who wish to find out the state of prison-estate.
- `/info`: provides information about the version of deployed application.

