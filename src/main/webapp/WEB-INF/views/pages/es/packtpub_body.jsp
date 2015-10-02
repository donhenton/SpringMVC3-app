<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="baseURL" value="/app/" />
<link href="js/angular/restaurant/libs/angular.js/angular-csp.css" rel="stylesheet" type="text/css"/>
<style>

    .suggestions {
        position: absolute;

        margin: 0;
        padding: 0 10px;
        background: #fff;
        border: 1px solid #eee;
    }
    .suggestions li {
        padding: .5em 1em;
        margin: 0;
        border-bottom: 1px solid #eee;
    }
    .suggestions small {
        color: #ccc;
    }
 


</style>

<div ng-app="mySearchApp" class="container" ng-controller="SearchResultsList">
    <div ng-cloak>
        <div class="row">
            <div class="offset1 span5">
                <form ng-submit="search()" class="form-inline" role="form">
                    <input type="text" ng-model="searchTerms" placeholder="Search for books" class="form-control" ng-keyup="evaluateTerms($event)" ng-blur="showAutocomplete = false">
                    <input type="submit" value="Search" class="btn btn-primary">
                </form>
            </div>
            <div class="span6">

                <ul class="suggestions" ng-show="showAutocomplete">
                    <li ng-repeat="suggestion in autocomplete.suggestions" ng-show="suggestion.options.length > 0">
                        <a href="#" ng-mousedown="searchForSuggestion()"><small>Search for &mdash;</small> {{suggestion.options[0].text}}</a>
                    </li>
                    <li ng-repeat="result in autocomplete.results">
                        <a href="#" ng-mousedown="searchForSingleTitle(result._source.title)">{{result._source.title}}</a>
                    </li>
                </ul>
            </div>
        </div>

        <section class="results">
            <h1 class="search-title">
                Results for <strong>{{results.searchTerms}}</strong> ({{results.documentCount}})
            </h1>

            <p ng-if="noResults" class="no-results">No results were found for your search.</p>

            <ul>
                <li ng-repeat="book in results.documents">
                    <a href="https://www.packtpub.com/all/?search={{book.title}}"><strong><span ng-bind-html="book.title"></span> &mdash; {{book.price_gbp}}</strong></a>
                    <p ng-bind-html="book['detailed description']"></p>
                </li>
            </ul>

            <button ng-click="getNextPage()" ng-if="canGetNextPage" class="btn btn-primary">Load More Results</button>

            <div class="throbber" ng-if="isSearching">
                SEARCHING &hellip;
            </div>
        </section>

        <section class="filters" ng-if="results.searchTerms">
            <strong>Sort by</strong><br>
            <select ng-model="$parent.selectedSort" ng-options="sort.displayName for sort in sortOptions" ng-change="updateSort()"></select>


        </section>
    </div>
    <script src="js/es/angular.js" type="text/javascript"></script>
    <script src="js/angular/restaurant/libs/angular.js/angular-resource.js" type="text/javascript"></script>
    <script src="js/angular/restaurant/libs/angular.js/angular-sanitize.js" type="text/javascript"></script>
    <script src="js/es/elasticsearch.angular.js" type="text/javascript"></script>
    <script src="js/es/packtpub/app.js" type="text/javascript"></script>
    <script src="js/es/packtpub/services/filterService.js" type="text/javascript"></script>
    <script src="js/es/packtpub//services/searchService.js" type="text/javascript"></script>
    <script src="js/es/packtpub/controllers/SearchResultsListController.js" type="text/javascript"></script>
</div>
 
